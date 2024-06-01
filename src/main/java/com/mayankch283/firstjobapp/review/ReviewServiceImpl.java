package com.mayankch283.firstjobapp.review;

import com.mayankch283.firstjobapp.company.Company;
import com.mayankch283.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    ReviewRepository reviewRepository;
    CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }
    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.findCompany(companyId);
        if(company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        Company company = companyService.findCompany(companyId);
        if(company != null){
            return reviewRepository.findById(reviewId).orElse(null);
        }
        return null;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        Review reviewToBeUpdated = getReview(companyId, reviewId);
        if(reviewToBeUpdated != null){
            reviewToBeUpdated.setTitle(review.getTitle());
            reviewToBeUpdated.setDescription(review.getDescription());
            reviewToBeUpdated.setRating(review.getRating());
            reviewRepository.save(reviewToBeUpdated);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        return false;
    }
}
