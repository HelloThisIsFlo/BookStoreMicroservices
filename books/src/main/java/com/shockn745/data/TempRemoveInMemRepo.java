package com.shockn745.data;

import com.shockn745.domain.model.book.Book;
import com.shockn745.domain.model.book.BookId;
import com.shockn745.domain.model.book.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
@Repository
public class TempRemoveInMemRepo implements BookRepository {

    private List<Book> dataStore = new ArrayList<>();

    @Override
    public void save(Book book) {

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
}
