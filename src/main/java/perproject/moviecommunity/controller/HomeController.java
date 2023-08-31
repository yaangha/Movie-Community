package perproject.moviecommunity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.service.ReviewService;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private final ReviewService reviewService;

    @GetMapping("/")
    public String home(Model model) {
        List<Review> review_list = reviewService.readReviewByStatus("1");

        model.addAttribute("review_list", review_list);

        return "member/homepage";
    }

}
