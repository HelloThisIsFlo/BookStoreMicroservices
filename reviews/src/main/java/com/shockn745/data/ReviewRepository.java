package com.shockn745.data;

import com.shockn745.domain.model.BookId;
import com.shockn745.domain.model.Review;
import com.shockn745.domain.model.ReviewId;

import java.util.List;

/**
 * @author Kempenich Florian
 */
public interface ReviewRepository {


    /*
     * Note to self:
     * For now only put operations needed. KISS
     *
     * Later when implementing new operations => See if a annoying to do or not (shouldn't be)
     */

    void save(Review toSave);

    void delete(Review toDelete);

    List<Review> findByBookid(BookId bookId);

    ReviewId generateNextId();
}
