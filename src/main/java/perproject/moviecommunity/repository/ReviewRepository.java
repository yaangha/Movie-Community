package perproject.moviecommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByOrderByIdAsc();
    List<Review> findByStatusOrderByIdDesc(String status);
    List<Review> findByMember(Member member);
    Optional<Review> findById(Long id);

    @Query(value = "update REVIEW r set r.title=:#{#review.title}, r.content=:#{#review.content}, r.status=:#{#review.status} where r.id=:#{#review.id}", nativeQuery = true)
    Review update(@Param(value = "review") Review review);
}
