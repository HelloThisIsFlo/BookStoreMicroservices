package com.shockn745.presentation.exception;

import com.shockn745.application.ExistingReviewException;
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

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ExistingReviewException.class)
    @ResponseBody
    public ErrorInfo handleExistingReview(HttpServletRequest request, ExistingReviewException ex) {
        LOG.error("Tried to add new review while existing review found! --> {}", ex.getExistingReview());
        return new ErrorInfo(request.getRequestURL().toString(), ex);
    }

}
