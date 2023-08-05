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
    @GetMapping("/")
    public String home(Model model) {
        List<Review> reviewList = reviewService.findReviews();
        model.addAttribute("reviewList", reviewList);
        return "/member/homepage";
    }

}
