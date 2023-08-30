package perproject.moviecommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.service.MemberService;
import perproject.moviecommunity.service.ReviewService;

import java.util.List;

@Controller
public class HomeController {

    private final ReviewService reviewService;
    private final MemberService memberService;

    @Autowired
    public HomeController(ReviewService reviewService, MemberService memberService) {
        this.reviewService = reviewService;
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Review> review_list = reviewService.readReviewByStatus("1");

        model.addAttribute("review_list", review_list);

//        return "/member/homepage";
        return "member/homepage";
    }

}
