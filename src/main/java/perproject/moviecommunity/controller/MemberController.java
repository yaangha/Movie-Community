package perproject.moviecommunity.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.domain.ReviewLike;
import perproject.moviecommunity.dto.MemberRegisterDto;
import perproject.moviecommunity.dto.MemberSecurityDto;
import perproject.moviecommunity.service.MemberService;
import perproject.moviecommunity.service.ReviewService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ReviewService reviewService;

    @GetMapping("join")
    public String join() {
        return "member/join";
    }

    @PostMapping("join")
    public String joinMember(@Valid MemberRegisterDto dto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("dto", dto.getUsername());
            Map<String, String> validResult = memberService.validateHandling(errors);

            for (String key : validResult.keySet()) {
                model.addAttribute(key, validResult.get(key));
            }

            return "member/join";
        }

        Member member = new Member();
        member.setUsername(dto.getUsername());
        member.setPassword(dto.getPassword());

        memberService.join(member);
        return "redirect:/login";
    }

    @GetMapping("login")
    public String loginBefore(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "exception", required = false) String exception,
                              Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "member/login";
    }

    @GetMapping("mypage")
    public String mypage(@AuthenticationPrincipal MemberSecurityDto memberSecurityDto, Model model) {
        Member member = memberService.findOne(memberSecurityDto.getUsername());
        List<Review> reviewAll = reviewService.findReviewByMember(member);
        List<Review> reviewSave = new ArrayList<>();
        List<Review> reviewRelease = new ArrayList<>();
        List<Review> reviewLikeList = reviewService.findReviewLikesFromMember(member.getId());

        for (Review r : reviewAll) {
            if (r.getStatus().equals("1")) {
                reviewRelease.add(r);
            } else if (r.getStatus().equals("0")) {
                reviewSave.add(r);
            }
        }

        model.addAttribute("reviewSave", reviewSave);
        model.addAttribute("reviewRelease", reviewRelease);
        model.addAttribute("reviewLikeList", reviewLikeList);

        return "member/mypage";
    }
}
