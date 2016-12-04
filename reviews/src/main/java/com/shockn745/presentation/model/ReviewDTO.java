package com.shockn745.presentation.model;

import com.google.common.base.MoreObjects;

/**
 * @author Kempenich Florian
 */
public class ReviewDTO {

    private String bookId;
    private String username;
    private int rating;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("bookId", bookId)
                .add("username", username)
                .add("rating", rating)
                .toString();
    }
}
