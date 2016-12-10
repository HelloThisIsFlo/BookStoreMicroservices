package com.shockn745.data.hibernate;

import com.shockn745.data.hibernate.model.BookJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kempenich Florian
 */
@Repository
public interface HibernateCrudBookRepository extends CrudRepository<BookJpaEntity, Long> {



}
