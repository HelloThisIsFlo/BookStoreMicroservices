package com.shockn745.presentation.model;

/**
 * @author Kempenich Florian
 */
public class BookDTO {

    public final String bookId;
    public final String title;
    public final String author;
    public final int numPages;
    public final double price;

    public BookDTO(String bookId, String title, String author, int numPages, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.numPages = numPages;
        this.price = price;
    }

}
