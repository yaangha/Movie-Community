package perproject.moviecommunity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.domain.ReviewLike;
import perproject.moviecommunity.dto.ReviewDto;
import perproject.moviecommunity.repository.MemberRepository;
import perproject.moviecommunity.repository.ReviewLikeRepository;
import perproject.moviecommunity.repository.ReviewRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ReviewLikeRepository reviewLikeRepository;

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
     * review를 물리적으로 삭제하지 않고 status 상태만 업데이트(2 = delete)
     */
    public Review delete(Long review_id) {
        Review review = reviewRepository.findById(review_id).get();
        review.setStatus("2");
        reviewRepository.update(review);
        return review;
    }

    /**
     * 전체 리뷰 조회시
     */
    public List<Review> findReviews() {
        return reviewRepository.findByOrderByIdAsc();
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
        if (dto.getTitle() == null) { // title이 null인 경우는 save-detail 페이지에서 release 버튼을 눌렀을 경우임
            review.setStatus(dto.getStatus());
        } else {
            review.setTitle(dto.getTitle());
            review.setContent(dto.getContent());
            review.setStatus(dto.getStatus());
        }

        review.setModified_time(LocalDateTime.now());

        return reviewRepository.update(review);
    }

    /**
     * 상태에 따른 리뷰 조회
     */
    public List<Review> readReviewByStatus(String status) {
        return reviewRepository.findByStatusOrderByIdDesc(status);
    }

    public int createReviewLike(Long member_id, Long review_id) {
        Member member = memberRepository.findById(member_id).get();
        Review review = reviewRepository.findById(review_id).get();

        ReviewLike reviewLike = new ReviewLike();
        reviewLike.setMember(member);
        reviewLike.setReview(review);
        ReviewLike saveReviewLike = reviewLikeRepository.save(reviewLike);

        return 1; // 정상적으로 추가되었으면 1을 리턴
    }

    public Optional<ReviewLike> findReviewLikeFromMember(Long member_id, Long review_id) {
        Optional<ReviewLike> reviewLike = reviewLikeRepository.findByMemberIdAndReviewId(member_id, review_id);

        return reviewLike;
    }

    public int deleteReviewLike(ReviewLike reviewLike) {
        reviewLikeRepository.delete(reviewLike);
        return 1; // 정상적으로 삭제되었으면 1을 리턴
    }

    public List<Review> findReviewLikesFromMember(Long member_id) {
        List<ReviewLike> reviewLikeList = reviewLikeRepository.findByMemberId(member_id);
        List<Review> list = reviewLikeList.stream().map(i -> changeReviewToReviewLike(i)).collect(Collectors.toList());
        return list;
    }

    public Review changeReviewToReviewLike(ReviewLike reviewLike) {
        Review review = reviewRepository.findById(reviewLike.getReview().getId()).get();
        return review;
    }
}
