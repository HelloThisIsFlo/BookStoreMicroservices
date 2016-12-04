package com.shockn745.application.impl;

import com.shockn745.application.ExistingReviewException;
import com.shockn745.application.ReviewService;
import com.shockn745.data.InMemoryRepository;
import com.shockn745.data.ReviewRepository;
import com.shockn745.domain.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Kempenich Florian
 */
public class ReviewServiceImplTest {

    private ReviewService reviewService;
    private ReviewRepository reviewRepository;

    private ReviewId id1;
    private ReviewId id2;
    private ReviewId id3;
    private ReviewId id4;

    @Before
    public void setUp() throws Exception {
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
        List<Review> result = reviewService.getAllReviewsForBook(new BookId("book-id"));

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
        assertEquals(44, fromRepo.getRating().value());
    }

    @Test
    public void writeNewReview_userAlreadyPublishedReviewForSpecificBook_exception() throws Exception {
        String username = "user22";
        String bookIdString = "the-book-with-a-review";
        int ratingValue = 57;

        // Prepare repo with review from user on book
        saveNewReviewInRepo(new ReviewId("randomId"), new BookId(bookIdString), username, ratingValue);

        // Prepare new review
        ReviewId id = reviewRepository.generateNextId();
        User user = new User(username);
        BookId bookId = new BookId(bookIdString);
        Rating rating = new Rating(23);


        // Should throw exception
        try {
            reviewService.writeNewReview(user, bookId, rating);
            fail("Should throw exception!");
        } catch (ExistingReviewException e) {
            assertEquals("There already is an existing review for this book", e.getMessage());
            Review existingReview = e.getExistingReview();

            assertEquals(username, existingReview.getReviewer().username());
            assertEquals(bookIdString, existingReview.getBookId().idString());
            assertEquals(ratingValue, existingReview.getRating().value());
        }
    }

    private void populateRepoWithFakeReviews() {
        saveNewReviewInRepo(id1, new BookId("book-id"), "patrick", 34);
        saveNewReviewInRepo(id2, new BookId("book-id"), "florian", 97);
        saveNewReviewInRepo(id3, new BookId("book-id"), "mark", 50);
        saveNewReviewInRepo(id4, new BookId("book-id2"), "greg", 23);
    }

    private void saveNewReviewInRepo(ReviewId id, BookId bookId, String username, int rating) {
        reviewRepository.save(new Review(id, bookId, new User(username), new Rating(rating)));
    }
}