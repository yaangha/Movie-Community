package perproject.moviecommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.repository.MemoryMemberRepository;

import java.util.List;

@Service
public class MemberService {

    private final MemoryMemberRepository memberRepository;

    @Autowired
    public MemberService(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 전체 회원 조회시 사용
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 이름으로 회원을 조회할 때 사용
     */
    public Member findOne(String name) {
        return memberRepository.findByName(name).get();
    }

    /**
     * 로그인할 때 사용
     */
    public Member login(String name, String pw) {
        Member member = memberRepository.findByName(name).get();
        if (pw.equals(member.getPw())) {
            return member;
        } else {
            return null;
        }
    }

    public Member findOneById(Long id) {
        return memberRepository.findById(id).get();
    }
}
