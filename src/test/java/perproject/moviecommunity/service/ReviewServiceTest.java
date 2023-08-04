package perproject.moviecommunity.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.repository.MemoryReviewRepository;
import perproject.moviecommunity.repository.ReviewRepository;

public class ReviewServiceTest {

    private ReviewService reviewService = new ReviewService();
    private ReviewRepository reviewRepository = new MemoryReviewRepository();

    private MemberService memberService = new MemberService();

    @Test
    public void create() {
        //given
        Member member = new Member();
        member.setName("apple");
        member.setPw("aaaa");

        Review review = new Review();
        review.setMember(member);
        review.setContent("i'm apple");

        //when
        memberService.join(member);
        Long reviewId = reviewService.create(review);

        //then
        Assertions.assertThat(review.getId()).isEqualTo(reviewId);
    }

}
