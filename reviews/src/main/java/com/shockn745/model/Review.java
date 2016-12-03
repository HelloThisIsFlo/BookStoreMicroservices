package com.shockn745.model;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents a book review.
 * The rating is in percentage, must be 0 <= rating <= 100
 *
 * @author Kempenich Florian
 */
public class Review {

    private Rating rating;
    private User reviewer;

    public Review(Rating rating, User reviewer) {
        setRating(rating);
        setReviewer(reviewer);
    }

    public int getRating() {
        return rating.value();
    }

    /**
     * Rating must be 0 <= rating <= 100
     * @param rating must be 0 <= rating <= 100
     */
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        checkNotNull(reviewer);
//        checkArgument
        this.reviewer = reviewer;
    }
}
