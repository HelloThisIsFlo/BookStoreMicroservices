package com.shockn745.domain.model.book;

import java.util.List;

/**
 * @author Kempenich Florian
 */
public interface BookRepository {

    /*
     * Note to self:
     * Notice how there is no 'getNextId()' method.
     * See note in 'Book' class.
     */

    void save(Book book);

    Book findById(BookId bookId);

    List<Book> findAll();
}
