package com.shockn745.application;

import com.shockn745.domain.model.book.*;

import java.util.List;

/**
 * @author Kempenich Florian
 */
public interface BookService {
    List<Book> getAllBooks();

    Book getBookDetails(BookId id) throws BookNotFound;

    BookId createNewBookEntry(Characteristics characteristics, Price price);
}
