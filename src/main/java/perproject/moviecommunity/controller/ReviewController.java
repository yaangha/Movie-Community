package perproject.moviecommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.dto.ReviewDto;
import perproject.moviecommunity.service.ReviewService;

import java.time.LocalDateTime;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("create")
    public String create() {
        return "/review/create";
    }

    @PostMapping("create")
    public String createReview(ReviewDto dto) {
        reviewService.create(dto);
        return "redirect:/";
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
        return "redirect:/";
    }
}
