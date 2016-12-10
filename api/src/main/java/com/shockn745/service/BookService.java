package com.shockn745.service;

import com.shockn745.model.Book;
import com.shockn745.model.BookWithAllReviews;
import com.shockn745.model.BookWithAvgReview;
import com.shockn745.model.Review;
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

    public List<BookWithAvgReview> getAllBooks() {
        List<Book> books = bookIntegration.getAll();

        List<BookWithAvgReview> result = new ArrayList<>(books.size());
        for (Book book : books) {
            result.add(new BookWithAvgReview(book, getAverage(book)));
        }

        return result;
    }

    private double getAverage(Book book) {
        List<Review> reviews = reviewIntegration.getAllForBook(book.getBookId());
        if (reviews.isEmpty()) {
            return 0;
        }

        double average = 0;
        for (Review review : reviews) {
            average += review.rating;
        }
        average = average/reviews.size();
        return average;
    }

    public BookWithAllReviews getBookDetails(String bookId) {
        Book book = bookIntegration.getBook(bookId);
        List<Review> reviews = reviewIntegration.getAllForBook(bookId);

        return new BookWithAllReviews(book, reviews);
    }

}
