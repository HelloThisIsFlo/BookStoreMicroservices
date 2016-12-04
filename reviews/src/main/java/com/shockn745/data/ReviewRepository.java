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

    List<Review> findByBookId(BookId bookId);

    /*
     * Note to self:
     * Not necessarily needed: Possible to simply use id field, with no constructor argument in Review.
     * == > Would obtain id after saving.
     *
     * Since creation and use are 2 separate actions, an entity is never used before being created . . . and persisted.
     * So whenever used, it has an id.
     *
     */
    ReviewId generateNextId();
}
