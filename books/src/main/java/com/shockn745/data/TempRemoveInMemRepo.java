package com.shockn745.data;

import com.shockn745.domain.model.book.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kempenich Florian
 */
@Repository
public class TempRemoveInMemRepo implements BookRepository {

    private List<Book> dataStore;

    public TempRemoveInMemRepo() {
        initWithFakeData();
    }

    private void initWithFakeData() {
        dataStore = new ArrayList<>(makeFakeDataSet());
    }

    @Override
    public void save(Book book) {
        BookId id = generateId();
        dataStore.add(book);
        book.setId(id);
    }

    private BookId generateId() {
        String id = Integer.toString(dataStore.size());
        return new BookId(id);
    }

    @Override
    public Book findById(BookId bookId) {
        return dataStore.stream()
                .filter(book -> book.id().sameValueAs(bookId))
                .findFirst()
                .orElse(Book.NULL);
    }

    @Override
    public List<Book> findAll() {
        return dataStore;
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
}
