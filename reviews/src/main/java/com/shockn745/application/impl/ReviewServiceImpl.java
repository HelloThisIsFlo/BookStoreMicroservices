package com.shockn745.application.impl;

import com.shockn745.application.ExistingReviewException;
import com.shockn745.application.ReviewService;
import com.shockn745.domain.model.review.*;
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
    public void writeNewReview(User user, BookId bookId, Rating rating) throws ExistingReviewException {

        Review existingReview = findExistingReview(bookId, user);
        if (!existingReview.sameIdentityAs(Review.NULL)) {
            throw new ExistingReviewException(existingReview);
        }

        ReviewId id = reviewRepository.generateNextId();
        Review review = new Review(id, bookId, user, rating);
        reviewRepository.save(review);
    }

    private Review findExistingReview(BookId bookId, User user) {
        return reviewRepository
                .findByBookId(bookId)
                .stream()
                .filter(review -> review.getReviewer().sameValueAs(user))
                .findFirst()
                .orElse(Review.NULL);
    }

    @Override
    public List<Review> getAllReviewsForBook(BookId bookId) {
        return reviewRepository.findByBookId(bookId);
    }
}
