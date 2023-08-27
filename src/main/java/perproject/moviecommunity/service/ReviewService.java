package perproject.moviecommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.dto.ReviewDto;
import perproject.moviecommunity.repository.MemoryMemberRepository;
import perproject.moviecommunity.repository.MemoryReviewRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final MemoryReviewRepository reviewRepository;
    private final MemoryMemberRepository memberRepository;

    @Autowired
    public ReviewService(MemoryReviewRepository reviewRepository, MemoryMemberRepository memberRepository) {
        this.reviewRepository = reviewRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * 리뷰 생성시
     */
    public Long create(ReviewDto dto) {
        Member member = memberRepository.findById(dto.getMember_id()).get();

        Review review = new Review();
        review.setMember(member);
        review.setTitle(dto.getTitle());
        review.setContent(dto.getContent());
        review.setStatus(dto.getStatus());
        review.setCreated_time(LocalDateTime.now());
        review.setModified_time(LocalDateTime.now());

        review = reviewRepository.save(review);

        return review.getId();
    }

    /**
     * 리뷰 삭제시
     */
    public Review delete(Long review_id) {
        Review review = reviewRepository.findById(review_id).get();
        return reviewRepository.remove(review.getId());
    }

    /**
     * 전체 리뷰 조회시
     */
    public List<Review> findReviews() {
        return reviewRepository.findAll();
    }

    /**
     * 리뷰 아이디로 조회시
     */
    public Optional<Review> findReviewByReviewId(Long review_id) {
        return reviewRepository.findById(review_id);
    }

    /**
     * 작성자 아이디로 조회시
     */
    public List<Review> findReviewByMember(Member member) {
        return reviewRepository.findByMember(member);
    }

    /**
     * 리뷰 수정시 사용
     */
    public Review modifyReview(Review review, ReviewDto dto) {
        review.setTitle(dto.getTitle());
        review.setContent(dto.getContent());
        review.setModified_time(LocalDateTime.now());

        return reviewRepository.update(review);
    }

    /**
     * 상태에 따른 리뷰 조회
     */
    public List<Review> readReviewByStatus(String status) {
        return reviewRepository.findReviewByStatus(status);
    }
}
