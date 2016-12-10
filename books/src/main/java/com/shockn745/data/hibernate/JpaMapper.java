package com.shockn745.data.hibernate;

import com.shockn745.data.hibernate.model.BookJpaEntity;
import com.shockn745.domain.model.book.Book;
import com.shockn745.domain.model.book.BookId;
import com.shockn745.domain.model.book.Characteristics;
import com.shockn745.domain.model.book.Price;

/**
 * @author Kempenich Florian
 */
public class JpaMapper {

    public BookJpaEntity map(Book book) {
        BookJpaEntity jpaEntity = new BookJpaEntity(
                book.characteristics().title(),
                book.characteristics().author(),
                book.characteristics().numPages(),
                book.price().amount()
        );

        jpaEntity.setId(parseId(book));

        return jpaEntity;
    }

    public Book map(BookJpaEntity bookJpaEntity) {
        Characteristics characteristics = new Characteristics(
                bookJpaEntity.getTitle(),
                bookJpaEntity.getAuthor(),
                bookJpaEntity.getNumPages()
        );
        Price price = new Price(bookJpaEntity.getPrice());

        Book book = new Book(characteristics, price);
        book.setId(getBookId(bookJpaEntity));

        return book;
    }

    private BookId getBookId(BookJpaEntity bookJpaEntity) {
        return new BookId(bookJpaEntity.getId().toString());
    }

    private Long parseId(Book book) {
        return Long.parseLong(book.id().idString());
    }
}
