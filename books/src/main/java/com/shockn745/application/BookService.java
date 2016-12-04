package com.shockn745.application;

import com.shockn745.domain.model.book.Book;
import com.shockn745.domain.model.book.BookId;
import com.shockn745.domain.model.book.BookNotFound;

import java.util.List;

/**
 * @author Kempenich Florian
 */
public interface BookService {
    List<Book> getAllBooks();

    Book getBookDetails(BookId id) throws BookNotFound;
}
