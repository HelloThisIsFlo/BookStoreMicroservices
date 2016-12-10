package com.shockn745.model;

/**
 * @author Kempenich Florian
 */
public class BookWithAvgReview {

    public Book book;
    public double avgReview;

    public BookWithAvgReview(Book book, double avgReview) {
        this.book = book;
        this.avgReview = avgReview;
    }

    public BookWithAvgReview() {
    }
}
