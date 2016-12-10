package com.shockn745.application.impl;

import com.shockn745.application.BookService;
import com.shockn745.domain.model.book.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kempenich Florian
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Autowired
    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book getBookDetails(BookId id) throws BookNotFound {
        Book book = repository.findById(id);

        if (book.equals(Book.NULL)) {
            throw new BookNotFound(id);
        }

        return book;
    }

    @Override
    public BookId createNewBookEntry(Characteristics characteristics, Price price) {

        Book book = new Book(characteristics, price);
        repository.save(book);
        return book.id();

    }
}
