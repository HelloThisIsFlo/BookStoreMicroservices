package com.shockn745.model;

import com.google.common.base.MoreObjects;

/**
 * @author Kempenich Florian
 */
public class Book {

    private String bookId;
    private String title;
    private String author;
    private int numPages;
    private double price;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("bookId", bookId)
                .add("title", title)
                .add("author", author)
                .add("numPages", numPages)
                .add("price", price)
                .toString();
    }
}
