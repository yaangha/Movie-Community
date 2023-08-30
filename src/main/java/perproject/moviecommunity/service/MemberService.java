package perproject.moviecommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.MemberRole;
import perproject.moviecommunity.repository.MemberRepository;
import perproject.moviecommunity.repository.MemoryMemberRepository;

import java.util.List;

@Service
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemoryMemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.addRole(MemberRole.USER);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 전체 회원 조회시 사용
     */
    public List<Member> findMembers() {
        return memberRepository.findByOrderByIdAsc();
    }

    /**
     * 이름으로 회원을 조회할 때 사용
     */
    public Member findOne(String name) {
        return memberRepository.findByUsername(name).get();
    }

    /**
     * 로그인할 때 사용
     */
    public Member login(String name, String pw) {
        Member member = memberRepository.findByUsername(name).get();
        if (pw.equals(member.getPassword())) {
            return member;
        } else {
            return null;
        }
    }

    public Member findOneById(Long id) {
        return memberRepository.findById(id).get();
    }
}
