package com.shockn745.application.impl;

import com.shockn745.application.ReviewService;
import com.shockn745.data.InMemoryRepository;
import com.shockn745.data.ReviewRepository;
import com.shockn745.domain.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Kempenich Florian
 */
public class ReviewServiceImplTest {

    private ReviewService reviewService;
    private ReviewRepository reviewRepository;

    private BookId bookId;
    private BookId bookId2;

    private ReviewId id1;
    private ReviewId id2;
    private ReviewId id3;
    private ReviewId id4;

    @Before
    public void setUp() throws Exception {
        bookId = new BookId("book-id");
        bookId2 = new BookId("book-id2");
        id1 = new ReviewId("id1");
        id2 = new ReviewId("id2");
        id3 = new ReviewId("id3");
        id4 = new ReviewId("id4");

        reviewRepository = new InMemoryRepository();
        reviewService = new ReviewServiceImpl(reviewRepository);

        populateRepoWithFakeReviews();
    }

    @Test
    public void testGetAllReviewsForBooks() throws Exception {
        List<Review> result = reviewService.getAllReviewsForBook(bookId);

        assertEquals(3, result.size());
        assertEquals(id1, result.get(0).getId());
        assertEquals(id2, result.get(1).getId());
        assertEquals(id3, result.get(2).getId());
    }

    @Test
    public void testWriteNewReview() throws Exception {
        BookId bookWithNewReview = new BookId("book-id-with-new-review");

        List<Review> beforeNewReview = reviewRepository.findByBookId(bookWithNewReview);

        reviewService.writeNewReview(new User("Frank"), bookWithNewReview, new Rating(44));

        List<Review> afterNewReview = reviewRepository.findByBookId(bookWithNewReview);

        assertEquals(0, beforeNewReview.size());
        assertEquals(1, afterNewReview.size());

        Review fromRepo = afterNewReview.get(0);
        assertEquals(bookWithNewReview, fromRepo.getBookId());
        assertEquals("Frank", fromRepo.getReviewer().username());
        assertEquals(44, fromRepo.getRating());
    }

    private void populateRepoWithFakeReviews() {
        saveNewReviewInRepo(id1, bookId, "patrick", 34);
        saveNewReviewInRepo(id2, bookId, "florian", 97);
        saveNewReviewInRepo(id3, bookId, "mark", 50);
        saveNewReviewInRepo(id4, bookId2, "greg", 23);
    }

    private void saveNewReviewInRepo(ReviewId id, BookId bookId, String username, int rating) {
        reviewRepository.save(new Review(id, bookId, new Rating(rating), new User(username)));
    }
}