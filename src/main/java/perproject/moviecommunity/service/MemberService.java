package perproject.moviecommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.MemberRole;
import perproject.moviecommunity.repository.MemberRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
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

    public Member findOneById(Long id) {
        return memberRepository.findById(id).get();
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validResult = new HashMap<>();

        for (FieldError e : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", e.getField());
            validResult.put(validKeyName, e.getDefaultMessage());
        }

        return validResult;
    }
}
