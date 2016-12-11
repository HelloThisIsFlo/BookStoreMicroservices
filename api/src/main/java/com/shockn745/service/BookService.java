package com.shockn745.service;

import com.shockn745.model.BookDto;
import com.shockn745.model.BookWithAllReviewsDto;
import com.shockn745.model.BookWithAvgReviewDto;
import com.shockn745.model.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
@Service
public class BookService {

    private final BookIntegration bookIntegration;
    private final ReviewIntegration reviewIntegration;

    @Autowired
    public BookService(BookIntegration bookIntegration, ReviewIntegration reviewIntegration) {
        this.bookIntegration = bookIntegration;
        this.reviewIntegration = reviewIntegration;
    }

    public List<BookWithAvgReviewDto> getAllBooks() {
        List<BookDto> books = bookIntegration.getAll();

        List<BookWithAvgReviewDto> result = new ArrayList<>(books.size());
        for (BookDto book : books) {
            result.add(new BookWithAvgReviewDto(book, getAverage(book)));
        }

        return result;
    }

    private double getAverage(BookDto book) {
        List<ReviewDto> reviews = reviewIntegration.getAllForBook(book.getBookId());
        if (reviews.isEmpty()) {
            return 0;
        }

        double average = 0;
        for (ReviewDto review : reviews) {
            average += review.rating;
        }
        average = average/reviews.size();
        return average;
    }

    public BookWithAllReviewsDto getBookDetails(String bookId) {
        BookDto book = bookIntegration.getBook(bookId);
        List<ReviewDto> reviews = reviewIntegration.getAllForBook(bookId);

        return new BookWithAllReviewsDto(book, reviews);
    }

}
