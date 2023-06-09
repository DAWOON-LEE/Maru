package com.bird.maru.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class TimeUtilTest {

    @Test
    @DisplayName("저번 경매 시작일 테스트")
    void previousAuctionStartDateTest() {
        // given
        LocalDateTime now = LocalDateTime.now();

        // when
        LocalDateTime previousMonday1AM = TimeUtil.getPreviousAuctionStartDate();

        // then
        log.debug("Now : {}\tAuctionStartDate : {}", now, previousMonday1AM);
        assertThat(previousMonday1AM).isBefore(now);
        assertThat(previousMonday1AM.getHour()).isEqualTo(1);
    }

    @Test
    @DisplayName("저번 경매 마감일 테스트")
    void previousAuctionEndTest() {
        // given
        LocalDateTime now = LocalDateTime.now();

        // when
        LocalDateTime previousAuctionStartDate = TimeUtil.getPreviousAuctionStartDate();
        LocalDateTime previousAuctionEndDate = TimeUtil.getPreviousAuctionEndDate(previousAuctionStartDate);

        // then
        log.debug("Now : {}\tAuctionEndDate : {}", now, previousAuctionEndDate);
        assertThat(previousAuctionEndDate).isBefore(now);
        assertThat(previousAuctionEndDate.getHour()).isEqualTo(23);
        assertThat(previousAuctionEndDate.getMinute()).isEqualTo(59);
        assertThat(previousAuctionEndDate.getSecond()).isEqualTo(59);
    }

    @Test
    @DisplayName("경매 기간 테스트")
    void auctionPeriodTest() {
        // given
        LocalDateTime now = LocalDateTime.now();

        // when
        LocalDateTime previousAuctionStartDate = TimeUtil.getPreviousAuctionStartDate();
        LocalDateTime previousAuctionEndDate = TimeUtil.getPreviousAuctionEndDate(previousAuctionStartDate);

        // then
        log.debug("startDate : {}\tendDate : {}", previousAuctionStartDate, previousAuctionEndDate);
        assertThat(previousAuctionStartDate).isBefore(previousAuctionEndDate);
        assertThat(ChronoUnit.DAYS.between(previousAuctionStartDate.toLocalDate(), previousAuctionEndDate.toLocalDate())).isEqualTo(6);
    }

    @Test
    @DisplayName("다음날 자정 시간 테스트")
    void nextMidNightTest() {
        // given
        LocalDateTime midnight = LocalDate.now().plusDays(1).atTime(LocalTime.MIDNIGHT);
        long expirationInSecs = midnight.toEpochSecond(ZoneOffset.UTC) - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

        // when
        log.debug("{} {}", midnight, expirationInSecs);

        // then
        assertThat(midnight).isAfter(LocalDateTime.now());
        assertThat(LocalDateTime.now().plusSeconds(expirationInSecs).getHour()).isEqualTo(midnight.getHour());
        assertThat(LocalDateTime.now().plusSeconds(expirationInSecs).getMinute()).isEqualTo(midnight.getMinute());
        assertThat(LocalDateTime.now().plusSeconds(expirationInSecs).getSecond()).isEqualTo(midnight.getSecond());
    }

}
