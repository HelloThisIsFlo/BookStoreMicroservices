package com.shockn745.data;

import com.shockn745.domain.model.BookId;
import com.shockn745.domain.model.Review;
import com.shockn745.domain.model.ReviewId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kempenich Florian
 */
// TODO: 12/3/2016 remove
public class TempFakeRepo implements ReviewRepository {

    private List<Review> database = new ArrayList<>();

    @Override
    public void save(Review toSave) {
        database.add(toSave);
    }

    @Override
    public void delete(Review toDelete) {
        database.remove(toDelete);
    }

    @Override
    public List<Review> findByBookId(BookId bookId) {
        return database.stream()
                .filter(review -> review.getBookId().sameValueAs(bookId))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewId generateNextId() {
        int id = database.size() + 1;
        return new ReviewId(Integer.toString(id));
    }
}