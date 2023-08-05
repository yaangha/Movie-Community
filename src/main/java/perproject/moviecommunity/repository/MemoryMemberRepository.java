package perproject.moviecommunity.repository;

import org.springframework.stereotype.Repository;
import perproject.moviecommunity.domain.Member;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> memberStore = new HashMap<>();
    private static long sequence = 0L;

    /**
     * 회원가입시 사용할 메서드
     * @param member 회원가입할 객체
     */
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        memberStore.put(member.getId(), member);
        return member;
    }

    /**
     * 전체 회원 조회시 사용
     */
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memberStore.values());
    }

    /**
     * id로 회원을 찾을 때 사용
     * @param id
     * @return
     */
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(memberStore.get(id));
    }

    /**
     * name으로 회원을 조회할 때 사용
     * @param name 조회할 회원의 이름
     */
    @Override
    public Optional<Member> findByName(String name) {
        return memberStore.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    /**
     * 회원 탈퇴시 사용
     */
    @Override
    public Member remove(Member member) {
        return memberStore.remove(member.getId());
    }
}
