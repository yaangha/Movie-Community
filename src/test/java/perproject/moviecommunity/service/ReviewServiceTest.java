package perproject.moviecommunity.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.dto.MemberDto;
import perproject.moviecommunity.dto.ReviewDto;
import perproject.moviecommunity.repository.MemoryMemberRepository;
import perproject.moviecommunity.repository.MemoryReviewRepository;
import perproject.moviecommunity.repository.ReviewRepository;

public class ReviewServiceTest {

    private MemoryReviewRepository reviewRepository = new MemoryReviewRepository();
    private MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private MemberService memberService = new MemberService(memberRepository, passwordEncoder);
    private ReviewService reviewService = new ReviewService(reviewRepository, memberRepository);

    @Test
    public void create() {
        //given
        Member member = new Member();
        member.setName("apple");
        member.setPw("aaaa");

        Review review = new Review();
        review.setMember(member);
        review.setContent("i'm apple");

        ReviewDto dto = new ReviewDto();
        dto.setTitle("test");
        dto.setContent("test");
        dto.setStatus("1");

        //when
        memberService.join(member);
        Long reviewId = reviewService.create(dto);

        //then
        Assertions.assertThat(review.getId()).isEqualTo(reviewId);
    }

}
