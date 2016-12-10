package com.shockn745.application.impl;

import com.shockn745.TestUtils;
import com.shockn745.application.BookService;
import com.shockn745.data.InMemoryRepository;
import com.shockn745.domain.model.book.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Kempenich Florian
 */
public class BookServiceTest {

    private InMemoryRepository repository;
    private BookService service;
    private TestUtils testUtils;

    @Before
    public void setUp() throws Exception {
        repository = new InMemoryRepository();
        service = new BookServiceImpl(repository);
        testUtils = new TestUtils();

        repository.initWithFakeData(makeFakeDataSet());
    }

    private List<Book> makeFakeDataSet() {
        return Arrays.asList(
                testUtils.makeBook("id-1", "title", "author", 321, 543.12),
                testUtils.makeBook("id-2", "Harry Potter", "author", 321, 124),
                testUtils.makeBook("id-3", "Ubiq", "Philip K. Dick", 450, 53.2),
                testUtils.makeBook("id-4", "Lost thing", "Famous writer", 1342, 789)
        );
    }

    @Test
    public void getAllTheBooks() throws Exception {
        List<Book> books = service.getAllBooks();

        assertEquals(4, books.size());
        assertEquals("id-1", books.get(0).id().idString());
        assertEquals("id-2", books.get(1).id().idString());
        assertEquals("id-3", books.get(2).id().idString());
        assertEquals("id-4", books.get(3).id().idString());
    }

    @Test
    public void getBookById() throws Exception {
        BookId id = new BookId("id-3");
        Book book = service.getBookDetails(id);

        assertEquals("Ubiq", book.characteristics().title());
        assertEquals("Philip K. Dick", book.characteristics().author());
        assertEquals(450, book.characteristics().numPages());
        assertEquals(53.2, book.price().amount(), 0);
    }

    @Test
    public void noBookFound_throwException() throws Exception {
        BookId inexistentId = new BookId("inexistent");

        try {
            service.getBookDetails(inexistentId);
            fail("Should throw exception");
        } catch (BookNotFound e) {
            assertEquals("Tried to get book, but id not found! --> id=inexistent", e.getMessage());
            assertEquals(inexistentId, e.getIdNotFound());
        }
    }

    @Test
    public void createNewBook() throws Exception {
        // Empty repository
        repository.initWithFakeData(new ArrayList<>());

        Characteristics characteristics = new Characteristics("New Book title", "new book author", 987);
        Price price = new Price(456.55);

        // Create new book
        BookId id = service.createNewBookEntry(characteristics, price);

        // Verify access to new book
        Book result = service.getBookDetails(id);
        assertEquals(id, result.id());
        assertEquals(characteristics, result.characteristics());
        assertEquals(price, result.price());
    }

}