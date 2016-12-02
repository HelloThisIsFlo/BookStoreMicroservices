package com.shockn745.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @author Kempenich Florian
 */
public class ReviewTest {

    User validUser;

    @Before
    public void setUp() throws Exception {
        validUser = new User("Patrick");

    }

    @Test(expected = NullPointerException.class)
    public void createWithoutUser_exception() throws Exception {
        new Review(3, null);
    }

    @Test
    public void setUserNull_exception() throws Exception {
        Review review = new Review(3, validUser);

        try {
            review.setReviewer(null);
            fail();
        } catch (Exception e) {
            // expected
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void ratingNegative_exception() throws Exception {
        new Review(-1, validUser);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ratingOver100_exception() throws Exception {
        new Review(101, validUser);
    }
}