package perproject.moviecommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perproject.moviecommunity.domain.ReviewLike;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {

    Optional<ReviewLike> findByMemberIdAndReviewId(Long member_id, Long review_id);
    List<ReviewLike> findByMemberId(Long member_id);
}
