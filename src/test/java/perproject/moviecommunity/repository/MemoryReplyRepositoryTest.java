package perproject.moviecommunity.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Reply;
import perproject.moviecommunity.domain.Review;

import java.util.List;

public class MemoryReplyRepositoryTest {

    ReplyRepository replyRepository = new MemoryReplyRepository();
    MemberRepository memberRepository = new MemoryMemberRepository();
    ReviewRepository reviewRepository = new MemoryReviewRepository();

    @Test
    public void create() {
        //given
        Member m1 = new Member();
        m1.setName("apple");
        m1.setPw("aaaa");
        Member mResult = memberRepository.save(m1);

        Review r1 = new Review();
        r1.setMember(m1);
        r1.setContent("i'm apple");
        Review rResult = reviewRepository.save(r1);

        Review r2 = new Review();
        r2.setMember(m1);
        r2.setContent("i'm apple!!!!");
        reviewRepository.save(r2);

        Reply reply1 = new Reply();
        reply1.setReview(r2);
        reply1.setMember(m1);
        reply1.setContent("it is a reply1");

        Reply reply2 = new Reply();
        reply2.setReview(r1);
        reply2.setMember(m1);
        reply2.setContent("it is a reply2");

        //when
        Reply replyResult1 = replyRepository.save(reply1);
        Reply replyResult2 = replyRepository.save(reply2);

        //then
        // Assertions.assertThat(reply).isEqualTo(replyResult1);
        List<Reply> replyR = replyRepository.findByReview(r2);

        for (Reply r : replyR) {
            System.out.println("r = " + r.toString());
        }

    }
}
