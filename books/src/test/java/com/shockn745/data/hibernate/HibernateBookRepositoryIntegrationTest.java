package com.shockn745.data.hibernate;

import com.shockn745.TestUtils;
import com.shockn745.domain.model.book.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Kempenich Florian
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-tests")
public class HibernateBookRepositoryIntegrationTest {

    // TODO: 12/10/2016 Also implement integration tests on service, or even controller

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private TestUtils testUtils;

    @Test
    public void saveAndFind() throws Exception {
        String id = "id-1";
        String title = "hello title";
        String author = "Frank";
        int numPage = 253;
        double price = 99.97;

        Book toSave = testUtils.makeBook(id, title, author, numPage, price);

        assertEquals(id, toSave.id().idString());
        bookRepository.save(toSave);
        BookId savedId = toSave.id();
        assertNotEquals(id, savedId.idString()); //Check that repository updates the id


        Book fromRepo = bookRepository.findById(savedId);

        assertEquals(savedId, fromRepo.id());
        assertEquals(title, fromRepo.characteristics().title());
        assertEquals(author, fromRepo.characteristics().author());
        assertEquals(numPage, fromRepo.characteristics().numPages());
        assertEquals(price, fromRepo.price().amount(), 0);
    }


    @Test
    public void saveMultiple_findAll() throws Exception {
        Book book1 = testUtils.makeBook("id-1", "title", "author", 39, 53.12);
        Book book2 = testUtils.makeBook("id-2", "Learn portuguese", "Alexandra", 321, 124);
        Book book3 = testUtils.makeBook("id-3", "Third book", "Philip", 665, 53.2);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        List<Book> fromRepo = bookRepository.findAll();

        assertEquals(3, fromRepo.size());
        assertListContainsSimilarBook(book1, fromRepo);
        assertListContainsSimilarBook(book2, fromRepo);
        assertListContainsSimilarBook(book3, fromRepo);
    }

    private void assertListContainsSimilarBook(Book toCheck, List<Book> bookList) {
        boolean contains = bookList.stream()
                .anyMatch(book -> areBooksSimilar(toCheck, book));

        assertTrue("List does not contain book: " + toCheck, contains);
    }

    private boolean areBooksSimilar(Book toCheck, Book book) {
        return toCheck.characteristics().title().equals(book.characteristics().title()) &&
                toCheck.characteristics().author().equals(book.characteristics().author()) &&
                toCheck.characteristics().numPages() == book.characteristics().numPages() &&
                Double.compare(toCheck.price().amount(), book.price().amount()) == 0;
    }
}