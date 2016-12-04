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

    }

    @Override
    public Book findById(BookId bookId) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public List<Book> findAll() {
        return dataStore;
    }

    public void initWithFakeData(List<Book> fakeDataSet) {
        dataStore = fakeDataSet;
    }
}
