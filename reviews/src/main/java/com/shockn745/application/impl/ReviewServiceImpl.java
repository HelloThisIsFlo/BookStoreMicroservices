package com.shockn745.application.impl;

import com.shockn745.application.ReviewService;
import com.shockn745.domain.model.BookId;
import com.shockn745.domain.model.Rating;
import com.shockn745.domain.model.Review;
import com.shockn745.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kempenich Florian
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Override
    public void writeNewReview(User user, BookId bookId, Rating rating) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public List<Review> getAllReviewsForBook(BookId bookId) {
        throw new RuntimeException("Not yet implemented");
    }
}
