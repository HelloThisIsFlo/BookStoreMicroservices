package com.shockn745.data.hibernate;

import com.shockn745.data.hibernate.model.BookJpaEntity;
import com.shockn745.domain.model.book.Book;
import com.shockn745.domain.model.book.BookId;
import com.shockn745.domain.model.book.BookRepository;
import com.shockn745.spring.IntegrationTests;
import com.shockn745.spring.Prod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
@Prod
@IntegrationTests
@Repository
public class HibernateBookRepository implements BookRepository {

    private final HibernateCrudBookRepository crudBookRepository;
    private final JpaMapper jpaMapper;

    @Autowired
    public HibernateBookRepository(HibernateCrudBookRepository crudBookRepository, JpaMapper jpaMapper) {
        this.crudBookRepository = crudBookRepository;
        this.jpaMapper = jpaMapper;
    }

    @Override
    public void save(Book book) {
        BookJpaEntity bookJpaEntity = jpaMapper.map(book);
        crudBookRepository.save(bookJpaEntity);

        Book saved = jpaMapper.map(bookJpaEntity);

        // Set id
        book.setId(saved.id());
    }

    @Override
    public Book findById(BookId bookId) {
        BookJpaEntity jpaEntity = crudBookRepository.findOne(parseId(bookId));
        return jpaMapper.map(jpaEntity);
    }

    private long parseId(BookId bookId) {
        return Long.parseLong(bookId.idString());
    }

    @Override
    public List<Book> findAll() {
        Iterable<BookJpaEntity> allJpaEntities = crudBookRepository.findAll();

        List<Book> result = new ArrayList<>();
        for (BookJpaEntity jpaEntity : allJpaEntities) {
            Book book = jpaMapper.map(jpaEntity);
            result.add(book);
        }

        return result;
    }
}
