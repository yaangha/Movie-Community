package perproject.moviecommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.service.ReviewService;

import java.util.List;

@Controller
public class HomeController {

    private final ReviewService reviewService;

    @Autowired
    public HomeController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // todo
    @GetMapping("/homepage")
    public String home(Long member_id, Model model) {
        List<Review> review_list = reviewService.findReviews();
        model.addAttribute("review_list", review_list);
        model.addAttribute("member_id", member_id);

        return "/member/homepage";
    }

}
