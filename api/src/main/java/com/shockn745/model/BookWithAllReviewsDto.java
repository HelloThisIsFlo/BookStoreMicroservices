package com.shockn745.model;

import java.util.List;

/**
 * @author Kempenich Florian
 */
public class BookWithAllReviewsDto {

    public BookDto book;
    public List<ReviewDto> reviews;

    public BookWithAllReviewsDto(BookDto book, List<ReviewDto> reviews) {
        this.book = book;
        this.reviews = reviews;
    }

    public BookWithAllReviewsDto() {
    }
}
