package com.shockn745.controller;

import com.shockn745.model.BookWithAllReviews;
import com.shockn745.model.BookWithAvgReview;
import com.shockn745.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Kempenich Florian
 */
@RestController
public class ApiController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public String info() {
        return "Ok";
    }

    @RequestMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<BookWithAvgReview> getAllBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping("/bookDetails")
    @ResponseStatus(HttpStatus.OK)
    public BookWithAllReviews getBookDetails(@RequestParam("bookId") String bookId) {
        return bookService.getBookDetails(bookId);
    }

}
