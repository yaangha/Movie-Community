package perproject.moviecommunity.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.repository.MemoryMemberRepository;

public class MemberServiceTest {

    private MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private MemberService memberService = new MemberService(memberRepository, passwordEncoder);

    @Test
    public void join() {
        // given
        Member member = new Member();
        member.setName("test1");
        member.setPw("1234");

        // when
        Long memberId = memberService.join(member);

        // then
        Member result = memberService.findOne(member.getName());
        Assertions.assertEquals(memberId, result.getId());
        System.out.println("result = " + result.getPw());
    }

    @Test
    public void login() {
        // given
        Member member = new Member();
        member.setName("yang");
        member.setPw("1234");
        memberService.join(member);

        // when
        Member result = memberService.login("yang", "1234");
        Member result2 = memberService.findOne("yang");

        // then
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member);
    }

}
