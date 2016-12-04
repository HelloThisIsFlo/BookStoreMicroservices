package com.shockn745.domain.model;

import com.google.common.base.MoreObjects;
import com.shockn745.domain.ddd.Entity;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents a book review.
 *
 * @author Kempenich Florian
 */
public class Review implements Entity<Review> {

    public static final Review NULL = new Review(
            new ReviewId("-1"),
            new BookId("-1"),
            new Rating(0),
            new User("-1")
    );

    private ReviewId id;
    private BookId bookId;
    private Rating rating;
    private User reviewer;

    // TODO: 12/4/2016 change constructor order
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

    public Rating getRating() {
        return rating;
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
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id.idString())
                .add("bookId", bookId.idString())
                .add("rating", rating.value())
                .add("reviewer", reviewer.username())
                .toString();
    }

    @Override
    public boolean sameIdentityAs(Review other) {
        return other.id.sameValueAs(id);
    }
}
