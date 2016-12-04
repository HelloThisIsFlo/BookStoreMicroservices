package com.shockn745.presentation.assembler;

import com.shockn745.domain.model.Review;
import com.shockn745.presentation.model.ReviewDTO;
import org.springframework.stereotype.Component;

/**
 * @author Kempenich Florian
 */
@Component
public class ReviewDTOAssembler {

    public ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();

        dto.setBookId(review.getBookId().idString());
        dto.setUsername(review.getReviewer().username());
        dto.setRating(review.getRating().value());

        return dto;
    }
}
