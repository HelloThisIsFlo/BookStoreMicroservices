package com.shockn745.presentation;

import com.shockn745.application.ExistingReviewException;
import com.shockn745.application.ReviewService;
import com.shockn745.domain.model.review.BookId;
import com.shockn745.domain.model.review.Rating;
import com.shockn745.domain.model.review.Review;
import com.shockn745.domain.model.review.User;
import com.shockn745.presentation.assembler.ReviewDTOAssembler;
import com.shockn745.presentation.model.ReviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kempenich Florian
 */

@RestController
public class ReviewController {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewController.class);

    private final ReviewService reviewService;
    private final ReviewDTOAssembler assembler;


    @Autowired
    public ReviewController(ReviewService reviewService, ReviewDTOAssembler assembler) {
        this.reviewService = reviewService;
        this.assembler = assembler;
    }

    @RequestMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDTO test(){
        ReviewDTO review = new ReviewDTO();

        review.setBookId("book-id");
        review.setUsername("shock");
        review.setRating(45);

        return review;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void saveNewReview(@RequestBody @Valid ReviewDTO newReview) throws ExistingReviewException {

        User user = new User(newReview.getUsername());
        BookId bookId = new BookId(newReview.getBookId());
        Rating rating = new Rating(newReview.getRating());

        reviewService.writeNewReview(user, bookId, rating);

        LOG.info("Saved: {}", newReview);
    }

    @RequestMapping(value = "/find")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> findAll(@RequestParam(value = "bookId") String bookId) {
        BookId id = new BookId(bookId);

        List<Review> reviews = reviewService.getAllReviewsForBook(id);

        return reviews
                .stream()
                .map(assembler::toDTO)
                .collect(Collectors.toList());
    }

}
