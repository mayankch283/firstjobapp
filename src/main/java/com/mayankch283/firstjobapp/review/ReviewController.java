package com.mayankch283.firstjobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        if(reviewService.addReview(companyId, review)){
            return new ResponseEntity<>("Review added successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("No company found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review = reviewService.getReview(companyId, reviewId);
        if(review!=null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review){
        if(reviewService.updateReview(companyId, reviewId, review)){
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No review found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){

        if(reviewService.getReview(companyId, reviewId)!=null){
            reviewService.getReview(companyId, reviewId);
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No review found", HttpStatus.NOT_FOUND);
    }
}
