package com.example.repairagencyspring.service;

import com.example.repairagencyspring.dto.ReviewDTO;
import com.example.repairagencyspring.model.Review;
import com.example.repairagencyspring.repository.OrderRepository;
import com.example.repairagencyspring.repository.ReviewRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderRepository orderRepository;

    private Logger logger = Logger.getLogger(ReviewService.class);

    public boolean addReview(ReviewDTO reviewDTO) {
        Review review = Review.builder()
                .content(reviewDTO.getContent())
                .dateWorkDone(reviewDTO.getDateWorkDone())
                .rating(reviewDTO.getRating())
                .userId(reviewDTO.getUserId())
                .orderId(reviewDTO.getOrderId())
                .build();
        reviewRepository.save(review);
        return true;
    }

    public List<ReviewDTO> getAllReviewsByUserId(long userId){
        List<Review> reviewList = reviewRepository.findAllByUserId(userId);
        return parsingReviewInReviewDTO(reviewList);
    }

    public List<ReviewDTO> getAll(){
        List<Review> reviewList = reviewRepository.getAll();
        return parsingReviewInReviewDTO(reviewList);
    }

    private List<ReviewDTO> parsingReviewInReviewDTO(List<Review> list) {
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for(Review review: list){
            reviewDTOS.add(ReviewDTO.builder()
                    .id(review.getId())
                    .content(review.getContent())
                    .dateWorkDone(review.getDateWorkDone())
                    .rating(review.getRating())
                    .userId(review.getUserId())
                    .orderId(review.getOrderId())
                    .build());
        }
        return reviewDTOS;
    }
}
