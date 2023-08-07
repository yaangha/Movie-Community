package perproject.moviecommunity.repository;

import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

    Review save(Review review);
    List<Review> findAll();
    List<Review> findReviewByStatus(String status);
    List<Review> findByMember(Member member);
    Optional<Review> findById(Long id);
    Review remove(Long id);
    Review update(Review review);
}
