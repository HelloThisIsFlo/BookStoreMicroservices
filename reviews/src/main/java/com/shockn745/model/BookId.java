package com.shockn745.model;

import com.shockn745.domain.ddd.ValueObject;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Kempenich Florian
 */
public class BookId implements ValueObject<BookId> {

    private String id;

    public BookId(String id) {
        checkNotNull(id);
        checkArgument(!id.isEmpty());
        this.id = id;
    }

    public String idString() {
        return id;
    }

    @Override
    public boolean sameValueAs(BookId other) {
        return other.id.equals(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookId bookId = (BookId) o;
        return sameValueAs(bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
