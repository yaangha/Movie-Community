package perproject.moviecommunity.service;

import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.repository.MemberRepository;
import perproject.moviecommunity.repository.MemoryMemberRepository;

import java.util.List;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
}
