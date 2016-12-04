package com.shockn745.presentation;

import com.shockn745.application.ReviewService;
import com.shockn745.domain.model.BookId;
import com.shockn745.domain.model.Rating;
import com.shockn745.domain.model.Review;
import com.shockn745.domain.model.User;
import com.shockn745.presentation.assembler.ReviewDTOAssembler;
import com.shockn745.presentation.model.ReviewDTO;
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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveNewReview(@RequestBody @Valid ReviewDTO newReview) {

        User user = new User(newReview.getUsername());
        BookId bookId = new BookId(newReview.getBookId());
        Rating rating = new Rating(newReview.getRating());

        reviewService.writeNewReview(user, bookId, rating);

        System.out.println("saved: " + newReview);
    }

    @RequestMapping(value = "/find")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> findAll(@RequestParam(value = "bookId", required = true) String bookId) {
        // TODO: 12/4/2016 Add validation to prevent empty book id before building 'BookId'

        BookId id = new BookId(bookId);

        List<Review> reviews = reviewService.getAllReviewsForBook(id);

        return reviews
                .stream()
                .map(assembler::toDTO)
                .collect(Collectors.toList());
    }

}
