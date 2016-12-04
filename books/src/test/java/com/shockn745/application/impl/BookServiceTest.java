package com.shockn745.application.impl;

import com.shockn745.application.BookService;
import com.shockn745.data.InMemoryRepository;
import com.shockn745.domain.model.book.*;
import org.junit.Before;
import org.junit.Test;

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

    @Before
    public void setUp() throws Exception {
        repository = new InMemoryRepository();
        service = new BookServiceImpl(repository);

        repository.initWithFakeData(makeFakeDataSet());
    }

    private List<Book> makeFakeDataSet() {
        return Arrays.asList(
                makeBook("id-1", "title", "author", 321, 543.12),
                makeBook("id-2", "Harry Potter", "author", 321, 124),
                makeBook("id-3", "Ubiq", "Philip K. Dick", 450, 53.2),
                makeBook("id-4", "Lost thing", "Famous writer", 1342, 789)
        );
    }

    private Book makeBook(String id, String title, String author, int numPage, double price) {
        Book book = new Book(
                new Characteristics(title, author, numPage),
                new Price(price)
        );
        book.setId(new BookId(id));
        return book;
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
            assertEquals("Book with not found! Id = inexistent", e.getMessage());
            assertEquals(inexistentId, e.getIdNotFound());
        }

    }
}