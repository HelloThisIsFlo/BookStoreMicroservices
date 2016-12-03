package com.shockn745.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

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
    public void createWithoutUser_exception() throws Exception {
        new Review(validRating, null);
    }

    @Test
    public void setUserNull_exception() throws Exception {
        Review review = new Review(validRating, validUser);

        try {
            review.setReviewer(null);
            fail();
        } catch (Exception e) {
            // expected
        }
    }

}