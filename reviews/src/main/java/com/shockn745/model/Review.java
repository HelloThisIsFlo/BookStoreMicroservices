package com.shockn745.model;

import com.shockn745.domain.ddd.Entity;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents a book review.
 * The rating is in percentage, must be 0 <= rating <= 100
 *
 * @author Kempenich Florian
 */
public class Review implements Entity<Review> {

    private ReviewId id;
    private BookId bookId;
    private Rating rating;
    private User reviewer;

    public Review(ReviewId id, BookId bookId, Rating rating, User reviewer) {
        this.id = checkNotNull(id);
        this.bookId = checkNotNull(bookId);
        setRating(rating);
        setReviewer(reviewer);
    }

    public int getRating() {
        return rating.value();
    }

    public void setRating(Rating rating) {
        this.rating = checkNotNull(rating);
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = checkNotNull(reviewer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return sameIdentityAs(review);
    }

    @Override
    public boolean sameIdentityAs(Review other) {
        return other.id.sameValueAs(id);
    }
}
