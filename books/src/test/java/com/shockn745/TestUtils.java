package com.shockn745;

import com.shockn745.domain.model.book.Book;
import com.shockn745.domain.model.book.BookId;
import com.shockn745.domain.model.book.Characteristics;
import com.shockn745.domain.model.book.Price;
import com.shockn745.spring.IntegrationTests;
import org.springframework.stereotype.Component;

/**
 * @author Kempenich Florian
 */
@Component
@IntegrationTests
public class TestUtils {

    public Book makeBook(String id, String title, String author, int numPage, double price) {
        Book book = new Book(
                new Characteristics(title, author, numPage),
                new Price(price)
        );
        book.setId(new BookId(id));
        return book;
    }
}
