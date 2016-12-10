package com.shockn745.data;

import com.shockn745.domain.model.book.Book;
import com.shockn745.domain.model.book.BookId;
import com.shockn745.domain.model.book.BookRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
public class InMemoryRepository implements BookRepository {

    private List<Book> dataStore = new ArrayList<>();

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

    public void initWithFakeData(List<Book> fakeDataSet) {
        dataStore = fakeDataSet;
    }
}
