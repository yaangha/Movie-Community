package perproject.moviecommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import perproject.moviecommunity.domain.Member;

import java.util.List;
import java.util.Optional;

//public interface MemberRepository {
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 인터페이스이기 때문에 추상 메서드만 존재 -> 상속 받은 클래스에서 구현할 것
    //Member save(Member member);
    List<Member> findByOrderByIdAsc();
    Optional<Member> findById(Long id);
    Optional<Member> findByUsername(String username);

    @Query(value = "delete from MEMBER m where m.id = :#{#member.id}", nativeQuery = true)
    Member remove(@Param(value = "member") Member member);
}
