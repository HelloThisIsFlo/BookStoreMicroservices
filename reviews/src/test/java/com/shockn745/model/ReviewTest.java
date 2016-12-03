package com.shockn745.model;

import com.google.common.testing.EqualsTester;
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
        new Review(new ReviewId("abc"), validRating, null);
    }

    @Test(expected = NullPointerException.class)
    public void setUserNull() throws Exception {
        Review review = new Review(new ReviewId("abc"), validRating, validUser);
        review.setReviewer(null);
    }

    @Test(expected = NullPointerException.class)
    public void ratingNull() throws Exception {
        new Review(new ReviewId("abc"), null, validUser);
    }

    @Test(expected = NullPointerException.class)
    public void setRatingNull() throws Exception {
        Review review = new Review(new ReviewId("abc"), validRating, validUser);
        review.setRating(null);
    }

    @Test(expected = NullPointerException.class)
    public void idNull() throws Exception {
        new Review(null, validRating, validUser);
    }

    @Test
    public void testEquality() throws Exception {
        // two reviews are equals when their ids are equal

        Rating validRating2 = new Rating(45);
        User validUser2 = new User("Florian");

        ReviewId id1 = new ReviewId("first-id");
        ReviewId id2 = new ReviewId("second-id");
        ReviewId id3 = new ReviewId("third-id");

        new EqualsTester()
                .addEqualityGroup(new Review(id1, validRating, validUser), new Review(id1, validRating, validUser))
                .addEqualityGroup(new Review(id2, validRating, validUser), new Review(id2, validRating, validUser))
                .addEqualityGroup(new Review(id3, validRating2, validUser2), new Review(id3, validRating, validUser))
                .testEquals();
    }
}