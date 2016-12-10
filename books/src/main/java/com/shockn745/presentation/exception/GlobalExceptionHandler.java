package com.shockn745.presentation.exception;

import com.shockn745.domain.model.book.BookNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kempenich Florian
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // No exception for now, just keeping for model

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(BookNotFound.class)
    @ResponseBody
    public ErrorInfo handleExistingReview(HttpServletRequest request, BookNotFound ex) {
        LOG.error("Tried to get book, but id not found! --> id={}", ex.getIdNotFound());
        return new ErrorInfo(request.getRequestURL().toString(), ex);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ErrorInfo handleExistingReview(HttpServletRequest request, IllegalArgumentException ex) {
        LOG.error("IllegalArgument exception:", ex);
        return new ErrorInfo(request.getRequestURL().toString(), ex);
    }

}
