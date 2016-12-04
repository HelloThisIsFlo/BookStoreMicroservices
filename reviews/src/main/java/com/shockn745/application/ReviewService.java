package com.shockn745.application;

import com.shockn745.domain.model.BookId;
import com.shockn745.domain.model.Rating;
import com.shockn745.domain.model.Review;
import com.shockn745.domain.model.User;

import java.util.List;

/**
 * @author Kempenich Florian
 */
public interface ReviewService {

    void writeNewReview(User user, BookId bookId, Rating rating) throws ExistingReviewException;

    List<Review> getAllReviewsForBook(BookId bookId);

    /*
     * Note to self:
     * Later implement below method
     *
     * void updateRating(ReviewId id, Rating newRating);
     *
     * Same with addComment(ReviewId id, Comment comment);
     */
}
