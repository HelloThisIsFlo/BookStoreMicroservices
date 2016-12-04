package com.shockn745.domain.model.book;

import com.google.common.base.MoreObjects;
import com.shockn745.domain.ddd.ValueObject;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Kempenich Florian
 */
public class Characteristics implements ValueObject<Characteristics> {

    private String title;
    private String author;
    private int numPages;

    public Characteristics(String title, String author, int numPages) {
        checkNotNull(title);
        checkNotNull(author);
        checkArgument(!title.isEmpty());
        checkArgument(!author.isEmpty());
        checkArgument(numPages > 0);

        this.title = title;
        this.author = author;
        this.numPages = numPages;
    }

    @Override
    public boolean sameValueAs(Characteristics other) {
        return other.title.equals(title) &&
                other.author.equals(author) &&
                other.numPages == numPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characteristics that = (Characteristics) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, numPages);
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public int numPages() {
        return numPages;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("title", title)
                .add("author", author)
                .add("numPages", numPages)
                .toString();
    }
}
