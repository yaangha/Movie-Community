package perproject.moviecommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.dto.MemberSecurityDto;
import perproject.moviecommunity.dto.ReviewDto;
import perproject.moviecommunity.service.MemberService;
import perproject.moviecommunity.service.ReviewService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final MemberService memberService;

    @Autowired
    public ReviewController(ReviewService reviewService, MemberService memberService) {
        this.reviewService = reviewService;
        this.memberService = memberService;
    }

    @GetMapping("create")
    public String create(Model model) {
        return "/review/create";
    }

    @PostMapping("create")
    public String createReview(@AuthenticationPrincipal MemberSecurityDto memberSecurityDto, ReviewDto dto) {
        Member member = memberService.findOne(memberSecurityDto.getUsername());
        dto.setMember_id(member.getId());
        Long review_id = reviewService.create(dto);
        return "redirect:/detail?review_id=" + review_id;
    }

    @GetMapping("modify")
    public String modify(Long review_id, Model model) {
        Review review = reviewService.findReviewByReviewId(review_id).get();
        model.addAttribute("review", review);
        return "/review/modify";
    }

    @PostMapping("modify")
    public String modifyReview(Long review_id, ReviewDto dto) {
        Review review = reviewService.findReviewByReviewId(review_id).get();
        reviewService.modifyReview(review, dto);
        return "redirect:/detail?review_id=" + review.getId();
    }

    @GetMapping("delete")
    public String deleteReview(Long review_id) {
        Review review = reviewService.delete(review_id);
        return "redirect:/";
    }

    @GetMapping("detail")
    public String detail(Long review_id, Model model) {
        Review review = reviewService.findReviewByReviewId(review_id).get();

        model.addAttribute("review", review);
        return "/review/detail";
    }
}
