package perproject.moviecommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;

import java.util.List;
import java.util.Optional;

//public interface ReviewRepository {
public interface ReviewRepository extends JpaRepository<Review, Long> {
    //Review save(Review review);
    List<Review> findByOrderByIdAsc();
    List<Review> findReviewByStatus(String status);
    List<Review> findByMember(Member member);
    Optional<Review> findById(Long id);

//    @Query(value = "delete from REVIEW r where r.id = :id", nativeQuery = true)
//    Review remove(@Param(value = "id") Long id);

    @Query(value = "update REVIEW r set r.title=:#{#review.title}, r.content=:#{#review.content}, r.status=:#{#review.status} where r.id=:#{#review.id}", nativeQuery = true)
    Review update(@Param(value = "review") Review review);
}
