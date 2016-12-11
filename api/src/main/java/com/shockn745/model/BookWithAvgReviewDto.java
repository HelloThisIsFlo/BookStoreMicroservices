package com.shockn745.model;

/**
 * @author Kempenich Florian
 */
public class BookWithAvgReviewDto {

    public BookDto book;
    public double avgReview;

    public BookWithAvgReviewDto(BookDto book, double avgReview) {
        this.book = book;
        this.avgReview = avgReview;
    }

    public BookWithAvgReviewDto() {
    }
}
