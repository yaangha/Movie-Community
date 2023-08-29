package perproject.moviecommunity.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
//public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review save(Review review);
    List<Review> findByOrderByIdAsc();
    List<Review> findReviewByStatus(String status);
    List<Review> findByMember(Member member);
    Optional<Review> findById(Long id);
    Review remove(Long id);
    Review update(Review review);
}
