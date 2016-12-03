package com.shockn745.model;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Kempenich Florian
 */
public class ReviewTest {

    private User validUser;
    private Rating validRating;

    @Before
    public void setUp() throws Exception {
        validUser = new User("Patrick");
        validRating = new Rating(3);
    }

    @Test(expected = NullPointerException.class)
    public void userNull() throws Exception {
        new Review(validRating, null);
    }

    @Test(expected = NullPointerException.class)
    public void setUserNull() throws Exception {
        Review review = new Review(validRating, validUser);
        review.setReviewer(null);
    }


    @Test(expected = NullPointerException.class)
    public void ratingNull() throws Exception {
        new Review(null, validUser);
    }

    @Test(expected = NullPointerException.class)
    public void setRatingNull() throws Exception {
        Review review = new Review(validRating, validUser);
        review.setRating(null);
    }
}