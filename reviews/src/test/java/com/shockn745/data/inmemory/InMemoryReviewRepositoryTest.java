package com.shockn745.data.inmemory;

import com.shockn745.data.ReviewRepository;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Kempenich Florian
 */
public class InMemoryReviewRepositoryTest {

    ReviewRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new InMemoryReviewRepository();
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testBasicOperations() throws Exception {


    }
}