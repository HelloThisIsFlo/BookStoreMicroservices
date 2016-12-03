package com.shockn745.model;

import com.google.common.testing.EqualsTester;
import org.junit.Test;

/**
 * @author Kempenich Florian
 */
public class ReviewIdTest {

    @Test(expected = NullPointerException.class)
    public void nullId() throws Exception {
        new ReviewId(null);
    }

    @Test
    public void testEquality() throws Exception {
        new EqualsTester()
                .addEqualityGroup(new ReviewId("asdf"), new ReviewId("asdf"))
                .addEqualityGroup(new ReviewId("dd"), new ReviewId("dd"))
                .addEqualityGroup(new ReviewId("DD"), new ReviewId("DD"))
                .testEquals();
    }
}