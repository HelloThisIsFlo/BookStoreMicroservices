package com.shockn745.model;

import java.util.List;

/**
 * @author Kempenich Florian
 */
public class BookWithAllReviews {

    public Book book;
    public List<Review> reviews;

    public BookWithAllReviews(Book book, List<Review> reviews) {
        this.book = book;
        this.reviews = reviews;
    }

    public BookWithAllReviews() {
    }
}
