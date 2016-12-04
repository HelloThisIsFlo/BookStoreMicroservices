package com.shockn745.domain.model;

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
        checkArgument(!id.isEmpty(), "Id cannot be empty");
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookId bookId = (BookId) o;
        return sameValueAs(bookId);
    }

    @Override
    public boolean sameValueAs(BookId other) {
        return Objects.equals(id, other.id);
    }

    public String idString() {
        return id;
    }
}
