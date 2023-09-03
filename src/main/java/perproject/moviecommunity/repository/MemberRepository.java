package perproject.moviecommunity.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import perproject.moviecommunity.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
//public interface MemberRepository extends JpaRepository<Member, Long> {

    // MemoryMemberRepository 필요
    Member save(Member member);
    Member remove(Member member);

    List<Member> findByOrderByIdAsc();
    Optional<Member> findById(Long id);
    Optional<Member> findByUsername(String username);
}
