package perproject.moviecommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.dto.MemberDto;
import perproject.moviecommunity.service.MemberService;
import perproject.moviecommunity.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final ReviewService reviewService;

    @Autowired
    public MemberController(MemberService memberService, ReviewService reviewService) {
        this.memberService = memberService;
        this.reviewService = reviewService;
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
        System.out.println(member.toString());
        return "redirect:/login";
    }

    @GetMapping("login")
    public String loginBefore() {
        return "/member/login";
    }

    @PostMapping("login")
    public String loginAfter(MemberDto dto) {
        Member member = memberService.login(dto.getName(), dto.getPw());
        return "redirect:/homepage?member_id=" + member.getId();
    }

    @GetMapping("mypage")
    public String mypage(Long member_id, Model model) {
        List<Review> reviewAll = reviewService.findReviewByMember(memberService.findOneById(member_id));
        List<Review> reviewSave = new ArrayList<>();
        List<Review> reviewRelease = new ArrayList<>();

        for (Review r : reviewAll) {
            if (r.getStatus().equals("1")) {
                reviewRelease.add(r);
            } else {
                reviewSave.add(r);
            }
        }

        model.addAttribute("reviewSave", reviewSave);
        model.addAttribute("reviewRelease", reviewRelease);
        model.addAttribute("member_id", member_id);

        return "/member/mypage";
    }
}
