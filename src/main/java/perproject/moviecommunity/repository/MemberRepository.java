package perproject.moviecommunity.repository;

import perproject.moviecommunity.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 인터페이스이기 때문에 추상 메서드만 존재 -> 상속 받은 클래스에서 구현할 것
    Member save(Member member);
    List<Member> findAll();
    Optional<Member> findById(Long id);
    Optional<Member> findByUsername(String username);
    Member remove(Member member);
}
