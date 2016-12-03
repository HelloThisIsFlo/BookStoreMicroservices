package com.shockn745.domain.model;

import com.shockn745.domain.ddd.ValueObject;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Kempenich Florian
 */
public final class Rating implements ValueObject<Rating> {

    private final int rating;

    public Rating(int rating) {
        checkArgument(rating >= 0);
        checkArgument(rating <= 100);
        this.rating = rating;
    }

    public int value() {
        return rating;
    }

    @Override
    public boolean sameValueAs(Rating other) {
        return other.rating == rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return sameValueAs(rating1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating);
    }
}
