package perproject.moviecommunity.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.domain.ReviewLike;

import java.time.LocalDateTime;
import java.util.List;

public class MemoryReviewRepositoryTest {

    ReviewRepository reviewRepository;
    MemberRepository memberRepository;

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setUsername("test1");
        member.setPassword("1234");
        memberRepository.save(member);

        Review review = new Review();
        review.setMember(member);
        review.setContent("this is test..");
        review.setCreated_time(LocalDateTime.now());
        review.setModified_time(LocalDateTime.now());

        //when
        reviewRepository.save(review);

        //then
        Review result = reviewRepository.findById(review.getId()).get();
        Assertions.assertThat(result).isEqualTo(review);
    }

    @Test
    public void findAll() {
        //given
        Member m1 = new Member();
        m1.setUsername("apple");
        m1.setPassword("aaaa");

        Member m2 = new Member();
        m2.setUsername("melon");
        m2.setPassword("mmmm");

        Review r1 = new Review();
        r1.setMember(m1);
        r1.setContent("i'm apple");
        r1.setCreated_time(null);
        r1.setModified_time(null);

        Review r2 = new Review();
        r2.setMember(m2);
        r2.setContent("i'm melon");
        r2.setCreated_time(null);
        r2.setModified_time(null);

        //when
        memberRepository.save(m1);
        memberRepository.save(m2);
        reviewRepository.save(r1);
        reviewRepository.save(r2);

        //then
        List<Review> reviewAll = reviewRepository.findAll();
        for (Review r : reviewAll) {
            System.out.println(r.toString());
        }
    }

    @Test
    public void find() {
        //given
        Member m1 = new Member();
        m1.setUsername("apple");
        m1.setPassword("aaaa");

        Member m2 = new Member();
        m2.setUsername("melon");
        m2.setPassword("mmmm");

        Review r1 = new Review();
        r1.setMember(m1);
        r1.setContent("i'm apple");
        r1.setCreated_time(null);
        r1.setModified_time(null);

        Review r2 = new Review();
        r2.setMember(m2);
        r2.setContent("i'm melon");
        r2.setCreated_time(null);
        r2.setModified_time(null);

        Review r3 = new Review();
        r3.setMember(m2);
        r3.setContent("i'm third");
        r3.setCreated_time(null);
        r3.setModified_time(null);

        //when
        memberRepository.save(m1);
        memberRepository.save(m2);
        reviewRepository.save(r1);
        reviewRepository.save(r2);
        reviewRepository.save(r3);

        //then
        List<Review> result = reviewRepository.findByMember(m2);
        for (Review r : result) {
            System.out.println("r.toString() = " + r.toString());
        }
    }

    @Test
    public void likeReview() {
        //given 로그인한 유저가 리뷰를 보고
        Member member = new Member();
        member.setUsername("test1");
        member.setPassword("1234");
        memberRepository.save(member);

        Review review = new Review();
        review.setMember(member);
        review.setContent("this is test..");
        review.setCreated_time(LocalDateTime.now());
        review.setModified_time(LocalDateTime.now());

        //when 좋아요 버튼을 눌렀을 때
        ReviewLike insertReviewList = clickReview(member.getId(), review.getId());

        //then 데이터 추가되는지
    }

    private ReviewLike clickReview(Long member_id, Long review_id) {
        ReviewLike reviewLike = new ReviewLike();
        Member member = memberRepository.findById(member_id).get();
        Review review = reviewRepository.findById(review_id).get();
        ReviewLike insertReviewList = reviewLike.updateLike(member, review);
        return insertReviewList;
    }

}
