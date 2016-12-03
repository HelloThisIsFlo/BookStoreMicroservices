package com.shockn745.data;

import com.shockn745.model.Review;

/**
 * @author Kempenich Florian
 */
public interface ReviewRepository {

    void add(Review item);

    void update(Review item);

    void remove(Review item);

//    List<Review> query(Specification specification);

}
