package perproject.moviecommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.dto.MemberDto;
import perproject.moviecommunity.service.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("join")
    public String join() {
        return "/member/join";
    }

    @PostMapping("join")
    public String joinMember(MemberDto dto) {
        Member member = new Member();
        member.setName(dto.getName());
        member.setPw(dto.getPw());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("login")
    public String loginBefore() {
        return "/member/login";
    }

    @PostMapping("login")
    public String loginAfter(MemberDto dto) {
        Member member = memberService.login(dto.getName(), dto.getPw());

        return "redirect:/";
    }
}
