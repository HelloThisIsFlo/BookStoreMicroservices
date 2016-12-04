package com.shockn745.domain.model.book;

/**
 * @author Kempenich Florian
 */
public class BookNotFound extends Exception {

    private final BookId idNotFound;

    public BookNotFound(BookId bookId) {
        super("Book with not found! Id = " + bookId.idString());
        idNotFound = bookId;
    }

    public BookId getIdNotFound() {
        return idNotFound;
    }
}
