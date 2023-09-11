package perproject.moviecommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perproject.moviecommunity.domain.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByOrderByIdAsc();
    Optional<Member> findById(Long id);
    Optional<Member> findByUsername(String username);
}
