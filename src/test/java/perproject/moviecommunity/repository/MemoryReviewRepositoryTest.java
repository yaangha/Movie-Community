package perproject.moviecommunity.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;

import java.time.LocalDateTime;
import java.util.List;

public class MemoryReviewRepositoryTest {

    MemoryReviewRepository reviewRepository = new MemoryReviewRepository();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("test1");
        member.setPw("1234");
        memberRepository.save(member);

        Review review = new Review();
        review.setMember(member);
        review.setContent("this is test..");
        review.setCreated_time(LocalDateTime.now());
        review.setModified_tile(LocalDateTime.now());

        //when
        reviewRepository.save(review);

        //then
        Review result = reviewRepository.findById(review.getId()).get();
        Assertions.assertThat(result).isEqualTo(review);
        System.out.println(review.toString());
        System.out.println(result.toString());
    }

    @Test
    public void findAll() {
        //given
        Member m1 = new Member();
        m1.setName("apple");
        m1.setPw("aaaa");

        Member m2 = new Member();
        m2.setName("melon");
        m2.setPw("mmmm");

        Review r1 = new Review();
        r1.setMember(m1);
        r1.setContent("i'm apple");
        r1.setCreated_time(null);
        r1.setModified_tile(null);

        Review r2 = new Review();
        r2.setMember(m2);
        r2.setContent("i'm melon");
        r2.setCreated_time(null);
        r2.setModified_tile(null);

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
        m1.setName("apple");
        m1.setPw("aaaa");

        Member m2 = new Member();
        m2.setName("melon");
        m2.setPw("mmmm");

        Review r1 = new Review();
        r1.setMember(m1);
        r1.setContent("i'm apple");
        r1.setCreated_time(null);
        r1.setModified_tile(null);

        Review r2 = new Review();
        r2.setMember(m2);
        r2.setContent("i'm melon");
        r2.setCreated_time(null);
        r2.setModified_tile(null);

        Review r3 = new Review();
        r3.setMember(m2);
        r3.setContent("i'm third");
        r3.setCreated_time(null);
        r3.setModified_tile(null);

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
}
