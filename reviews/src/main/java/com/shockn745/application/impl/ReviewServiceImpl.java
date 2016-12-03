package com.shockn745.application.impl;

import com.shockn745.application.ReviewService;
import com.shockn745.data.ReviewRepository;
import com.shockn745.domain.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kempenich Florian
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void writeNewReview(User user, BookId bookId, Rating rating) {
        ReviewId id = reviewRepository.generateNextId();
        Review review = new Review(id, bookId, rating, user);
        reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviewsForBook(BookId bookId) {
        return reviewRepository.findByBookId(bookId);
    }
}
