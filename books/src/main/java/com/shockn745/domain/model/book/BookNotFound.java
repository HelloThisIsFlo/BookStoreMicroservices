package com.shockn745.domain.model.book;

/**
 * @author Kempenich Florian
 */
public class BookNotFound extends Exception {

    private final BookId idNotFound;

    public BookNotFound(BookId bookId) {
        super("Tried to get book, but id not found! --> id=" + bookId.idString());
        idNotFound = bookId;
    }

    public BookId getIdNotFound() {
        return idNotFound;
    }
}
