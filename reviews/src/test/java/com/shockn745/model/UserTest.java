package com.shockn745.model;

import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @author Kempenich Florian
 */
public class UserTest {

    @Test(expected = NullPointerException.class)
    public void createWithNameNull_exception() throws Exception {
        new User(null);
    }

    @Test
    public void setNameNull_exception() throws Exception {
        User user = new User("patrick");

        try {
            user.setUsername(null);
            fail();
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void setNameEmpty_exception() throws Exception {
        User user = new User("firstName");

        try {
            user = new User("");
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNameEmpty_exception() throws Exception {
        User user = new User("");
    }
}