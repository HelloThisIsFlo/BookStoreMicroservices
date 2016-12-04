package com.shockn745.data;

import com.shockn745.domain.model.review.BookId;
import com.shockn745.domain.model.review.Review;
import com.shockn745.domain.model.review.ReviewId;
import com.shockn745.domain.model.review.ReviewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kempenich Florian
 */
public class InMemoryRepository implements ReviewRepository {

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