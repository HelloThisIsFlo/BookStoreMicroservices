package com.shockn745.data.hibernate;

import com.shockn745.domain.model.book.Book;
import com.shockn745.domain.model.book.BookId;
import com.shockn745.domain.model.book.BookRepository;
import com.shockn745.spring.Prod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
@Prod
@Repository
public class HibernateBookRepository implements BookRepository {

    private final HibernateCrudBookRepository crudBookRepository;

    @Autowired
    public HibernateBookRepository(HibernateCrudBookRepository crudBookRepository) {
        this.crudBookRepository = crudBookRepository;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public Book findById(BookId bookId) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>();
    }
}
