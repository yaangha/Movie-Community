package perproject.moviecommunity.repository;

import org.springframework.stereotype.Repository;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryReviewRepository implements ReviewRepository {

    private static Map<Long, Review> reviewStore = new HashMap<>();
    private static long sequence = 0L;

    /**
     * 리뷰 저장
     */
    @Override
    public Review save(Review review) {
        review.setId(++sequence);
        reviewStore.put(review.getId(), review);
        return review;
    }

    /**
     * 전체 리뷰 조회시 사용
     */
    @Override
    public List<Review> findByOrderByIdAsc() {
        return new ArrayList<>(reviewStore.values());
    }

    /**
     * 임시저장 또는 발행된 리뷰 조회
     */
    @Override
    public List<Review> findByStatusOrderByIdDesc(String status) {
        return reviewStore.values()
                .stream()
                .filter(r -> r.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> findByMember(Member member) {
        return reviewStore.values()
                .stream()
                .filter(r -> r.getMember().getId() == member.getId())
                .collect(Collectors.toList());
    }

    /**
     * id로 리뷰 조회할 떄 사용
     */
    @Override
    public Optional<Review> findById(Long id) {
        return Optional.ofNullable(reviewStore.get(id));
    }

    /**
     * 리뷰 삭제할 때 사용
     */
    @Override
    public Review remove(Long id) {
        return reviewStore.remove(id);
    }

    /**
     * 리뷰 수정시 사용
     */
    @Override
    public Review update(Review review) {
        return reviewStore.replace(review.getId(), review);
    }
}