package com.bird.maru.notice.service;

import com.bird.maru.domain.model.entity.Member;
import com.bird.maru.notice.model.Category;
import com.bird.maru.notice.model.Message;
import com.bird.maru.notice.model.Notice;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeServiceTest {

    @Autowired
    private NoticeServiceImpl noticeService;

    @Test
    @DisplayName("알림_저장_단건")
    void noticeSaveTest() {
        //given
        final Member member = Member.builder().alarmToken("test_token").id(1L).build();
        final Message message = new Message();
        message.setCategory(Category.AUCTION);
        message.setContent("테스트!");

        //when
        noticeService.saveNotice(member, message);
        final List<Notice> notices = noticeService.findByMemberId(member.getId());

        //then
        Assertions.assertThat(notices).hasSize(1);
        Assertions.assertThat(notices.get(0).getNoticeToken()).isEqualTo("test_token");
    }

}