package com.bird.maru.spot.service.query;

import static org.assertj.core.api.Assertions.assertThat;

import com.bird.maru.common.util.RandomUtil;
import com.bird.maru.domain.model.entity.Member;
import com.bird.maru.domain.model.entity.Scrap;
import com.bird.maru.domain.model.entity.Spot;
import com.bird.maru.domain.model.entity.SpotHasTag;
import com.bird.maru.domain.model.entity.Tag;
import com.bird.maru.domain.model.type.Coordinate;
import com.bird.maru.domain.model.type.Image;
import com.bird.maru.domain.model.type.Provider;
import com.bird.maru.member.repository.MemberRepository;
import com.bird.maru.scrap.repository.ScrapRepository;
import com.bird.maru.scrap.repository.query.ScrapQueryRepository;
import com.bird.maru.spot.controller.dto.SpotSearchCondition;
import com.bird.maru.tag.repository.SpotHasTagRepository;
import com.bird.maru.spot.repository.SpotRepository;
import com.bird.maru.spot.repository.query.dto.SpotSimpleDto;
import com.bird.maru.tag.repository.TagRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpotQueryServiceImplTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private ScrapRepository scrapRepository;

    @Autowired
    private ScrapQueryRepository scrapQueryRepository;

    @Autowired
    private SpotQueryService spotQueryService;

    @Autowired
    private SpotHasTagRepository spotHasTagRepository;

    @BeforeEach
    void beforeEach() {
        Member testMember1 = Member.builder()
                                   .nickname("test1")
                                   .email("test1@gmail.com")
                                   .provider(Provider.GOOGLE)
                                   .build();
        memberRepository.save(testMember1);

        Member testMember2 = Member.builder()
                                   .nickname("test2")
                                   .email("test2@naver.com")
                                   .provider(Provider.NAVER)
                                   .build();
        memberRepository.save(testMember2);

        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tags.add(
                    Tag.builder()
                       .name("dummy " + i)
                       .build()
            );
        }
        tagRepository.saveAll(tags);

        List<Spot> spots = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            spots.add(
                    Spot.builder()
                        .member((i & 1) != 0 ? testMember1 : testMember2)
                        .image(Image.getDefaultMemberProfile())
                        .coordinate(new Coordinate(Math.random() * 90, Math.random() * 90))
                        .build()
            );
        }
        spotRepository.saveAll(spots);

        List<SpotHasTag> spotHasTags = new ArrayList<>();
        for (Spot cur : spots) {
            spotHasTags.add(
                    SpotHasTag.builder()
                              .spot(cur)
                              .tag(RandomUtil.randomElement(tags))
                              .build()
            );
        }
        spotHasTagRepository.saveAll(spotHasTags);

        List<Scrap> scraps = new ArrayList<>();
        for (Spot cur : spots) {
            scraps.add(
                    Scrap.builder()
                         .member(cur.getMember() == testMember2 ? testMember1 : testMember2)
                         .spot(cur)
                         .build()
            );
        }
        scrapRepository.saveAll(scraps);
    }

    @AfterEach
    void afterEach() {
        scrapRepository.deleteAllInBatch();
        spotHasTagRepository.deleteAllInBatch();
        spotRepository.deleteAllInBatch();
        tagRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("내 스크랩 목록 조회 테스트")
    void findMyScrapsTest() {
        // given
        Member testMember = memberRepository.findAll().get(0);
        long lastOffset = spotRepository.findAll().get(40).getId();
        int pageSize = 20;

        // when
        List<SpotSimpleDto> spotDtos = spotQueryService.findMyScraps(
                testMember.getId(), SpotSearchCondition.builder()
                                                       .lastOffset(lastOffset)
                                                       .size(pageSize)
                                                       .mine(Boolean.FALSE)
                                                       .scraped(Boolean.TRUE)
                                                       .build()
        );

        // then
        Set<Long> scraped = scrapQueryRepository.findSpotIdsByMemberAndSpotIn(testMember.getId(), spotDtos.stream()
                                                                                                          .map(SpotSimpleDto::getId)
                                                                                                          .collect(Collectors.toList()));
        assertThat(
                spotDtos.stream()
                        .filter(spot -> scraped.contains(spot.getId()))
                        .collect(Collectors.toList())
        ).hasSize(pageSize);
    }

}