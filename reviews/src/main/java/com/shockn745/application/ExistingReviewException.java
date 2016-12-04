package com.shockn745.application;

import com.shockn745.domain.model.review.Review;

/**
 * @author Kempenich Florian
 */
public class ExistingReviewException extends Exception {

    private Review existingReview;

    public ExistingReviewException(Review existingReview) {
        super("There already is an existing review for this book! --> " + existingReview.toString());
        this.existingReview = existingReview;
    }

    public Review getExistingReview() {
        return existingReview;
    }
}
