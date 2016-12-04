package com.shockn745.domain.model.review;

import com.google.common.testing.EqualsTester;
import com.shockn745.domain.model.review.BookId;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Kempenich Florian
 */
public class BookIdTest {

    @Test(expected = NullPointerException.class)
    public void nullId() throws Exception {
        new BookId(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyId() throws Exception {
        new BookId("");
    }

    @Test
    public void testEquality() throws Exception {
        new EqualsTester()
                .addEqualityGroup(new BookId("id"), new BookId("id"))
                .addEqualityGroup(new BookId("asdf"), new BookId("asdf"))
                .addEqualityGroup(new BookId("ASDF"), new BookId("ASDF"))
                .testEquals();
    }

    @Test
    public void idString() throws Exception {
        String id = "idString";
        BookId bookId = new BookId(id);
        assertEquals(id, bookId.idString());
    }
}