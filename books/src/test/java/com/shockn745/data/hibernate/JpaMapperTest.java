package com.shockn745.data.hibernate;

import com.shockn745.TestUtils;
import com.shockn745.data.hibernate.model.BookJpaEntity;
import com.shockn745.domain.model.book.Book;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Kempenich Florian
 */
public class JpaMapperTest {

    private JpaMapper jpaMapper;
    private TestUtils testUtils;

    @Before
    public void setUp() throws Exception {
        jpaMapper = new JpaMapper();
        testUtils = new TestUtils();
    }

    @Test
    public void mapToEntity() throws Exception {
        String title = "asdf";
        String author = "wetroij";
        String bookId = "345";
        int numPages = 5553;
        double price = 34.5;

        Book book = testUtils.makeBook(bookId, title, author, numPages, price);

        BookJpaEntity jpaEntity = jpaMapper.map(book);

        assertEquals(title, jpaEntity.getTitle());
        assertEquals(author, jpaEntity.getAuthor());
        assertEquals(numPages, jpaEntity.getNumPages());
        assertEquals(price, jpaEntity.getPrice(), 0);
    }

    @Test
    public void shouldEraseId_whenMappingToEntity() throws Exception {
        Book book = testUtils.makeDefaultBook();

        BookJpaEntity jpaEntity = jpaMapper.map(book);
        assertNull(jpaEntity.getId());
    }

    private long parseId(Book book) {
        return Long.parseLong(book.id().idString());
    }

    @Test
    public void mapToDomainObject() throws Exception {
        String title = "asdf";
        String author = "wetroij";
        long bookId = 345;
        int numPages = 5553;
        double price = 34.5;

        BookJpaEntity bookJpaEntity = new BookJpaEntity(
                title,
                author,
                numPages,
                price
        );
        bookJpaEntity.setId(bookId);

        Book domainBook = jpaMapper.map(bookJpaEntity);

        assertEquals(Long.toString(bookId), domainBook.id().idString());

        assertEquals(title, domainBook.characteristics().title());
        assertEquals(author, domainBook.characteristics().author());
        assertEquals(numPages, domainBook.characteristics().numPages());
        assertEquals(price, domainBook.price().amount(), 0);
    }


}