package perproject.moviecommunity.service;

import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.repository.MemoryReviewRepository;
import perproject.moviecommunity.repository.ReviewRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class ReviewService {

    private final ReviewRepository reviewRepository = new MemoryReviewRepository();

    /**
     * 리뷰 생성시
     */
    public Long create(Review review) {
        reviewRepository.save(review);
        return review.getId();
    }

    /**
     * 리뷰 삭제시
     */
    public Long delete(Review review) {
        reviewRepository.remove(review.getId());
        return review.getId();
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
    public Review modifyReview(Review review) {
        return reviewRepository.update(review);
    }
}
