package com.bird.maru.cloud.aws.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.bird.maru.domain.model.type.Coordinate;
import com.bird.maru.domain.model.type.Image;
import com.bird.maru.spot.service.dto.SpotImage;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class AwsS3Service {

    private static final String MEMBER_PATH = "images/members";
    private static final String SPOT_PATH = "images/spots";

    private final AmazonS3 amazonS3;
    private final String bucketName;

    public AwsS3Service(AmazonS3 amazonS3, @Value("${cloud.aws.s3.bucket}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }

    /**
     * 멤버의 프로필 사진을 저장합니다.
     *
     * @param image 저장할 1개의 프로필 사진
     * @return Image 객체를 반환합니다. 이 객체는 {@code S3Object}에 대한 기본 정보(저장 경로, URL)를 담고 있습니다.
     */
    public Image uploadMemberProfileImage(MultipartFile image) {
        try (InputStream input = image.getInputStream()) {
            return upload(
                    input,
                    createKey(MEMBER_PATH, Objects.requireNonNull(image.getOriginalFilename())),
                    getObjectMetadata(image)
            );
        } catch (IOException e) {
            throw new IllegalArgumentException("업로드 하려는 파일을 읽을 수 없습니다.", e);
        }
    }

    /**
     * 스팟 사진을 저장합니다.
     *
     * @param image 저장할 1개의 스팟 사진
     * @return Image 객체를 반환합니다. 이 객체는 {@code S3Object}에 대한 기본 정보(저장 경로, URL)를 담고 있습니다.
     */
    public SpotImage uploadSpotImage(MultipartFile image) {
        Coordinate coordinate;
        Image uploaded;

        try (InputStream input = image.getInputStream()) {
            coordinate = getGpsInfo(input);
        } catch (ImageProcessingException | IOException e) {
            throw new IllegalArgumentException("업로드 하려는 파일을 읽을 수 없습니다.", e);
        }

        try (InputStream input = image.getInputStream()) {
            uploaded = upload(
                    input,
                    createKey(SPOT_PATH, Objects.requireNonNull(image.getOriginalFilename())),
                    getObjectMetadata(image)
            );
        } catch (IOException e) {
            throw new IllegalArgumentException("업로드 하려는 파일을 읽을 수 없습니다.", e);
        }

        return SpotImage.builder()
                        .coordinate(coordinate)
                        .image(uploaded)
                        .build();
    }

    /**
     * 멤버의 프로필 사진을 수정합니다.
     *
     * @param image     교체할 1개의 프로필 사진
     * @param imageInfo 수정할 사진의 정보
     * @return Image 객체를 반환합니다. 이 객체는 {@code S3Object}에 대한 기본 정보(저장 경로, URL)를 담고 있습니다.
     */
    public Image updateMemberProfileImage(MultipartFile image, Image imageInfo) {
        deleteFile(imageInfo.getSavedPath());
        return uploadMemberProfileImage(image);
    }

    /**
     * S3에 저장된 파일을 다운로드합니다.
     *
     * @param key S3에서 파일이 저장된 경로
     * @return 파일을 byte 배열로 반환합니다. 파일의 타입은 key를 파싱하여 얻어야 합니다.
     * @throws UncheckedIOException 파일을 다운로드하여 byte 배열로 변환하는 중에 이 예외가 발생할 수 있습니다.
     */
    public byte[] downloadFile(String key) {
        try (
                S3ObjectInputStream inputStream = amazonS3.getObject(this.bucketName, key)
                                                          .getObjectContent()
        ) {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * S3에 저장된 파일을 삭제합니다.
     *
     * @param key S3에서 파일이 저장된 경로
     */
    public void deleteFile(String key) {
        amazonS3.deleteObject(this.bucketName, key);
    }

    private Image upload(InputStream input, String key, ObjectMetadata objectMetadata) {
        amazonS3.putObject(
                new PutObjectRequest(
                        this.bucketName, key, input, objectMetadata
                ).withCannedAcl(CannedAccessControlList.PublicRead)
        );

        return Image.builder()
                    .savedPath(key)
                    .url(amazonS3.getUrl(this.bucketName, key))
                    .build();
    }

    private Coordinate getGpsInfo(InputStream input) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(input);
        GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

        if (gpsDirectory == null || gpsDirectory.getGeoLocation() == null) {
            throw new ImageProcessingException("GPS 데이터를 불러올 수 없습니다.");
        }

        GeoLocation location = gpsDirectory.getGeoLocation();
        return Coordinate.builder()
                         .lng(location.getLongitude())
                         .lat(location.getLatitude())
                         .build();
    }

    private String createKey(String path, String originalFilename) {
        return path + "/" + UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
    }

    private ObjectMetadata getObjectMetadata(MultipartFile multipartFile) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        return metadata;
    }

}
