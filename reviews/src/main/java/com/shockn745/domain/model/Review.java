package com.shockn745.domain.model;

import com.shockn745.domain.ddd.Entity;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents a book review.
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
        updateRating(rating);
        this.reviewer = checkNotNull(reviewer);
    }

    public ReviewId getId() {
        return id;
    }

    public BookId getBookId() {
        return bookId;
    }

    public int getRating() {// TODO: 12/3/2016 change to Rating type
        return rating.value();
    }

    public void updateRating(Rating rating) {
        this.rating = checkNotNull(rating);
    }

    public User getReviewer() {
        return reviewer;
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
