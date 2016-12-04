package com.shockn745.presentation.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author Kempenich Florian
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // No exception for now, just keeping for model

//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler(ExistingReviewException.class)
//    @ResponseBody
//    public ErrorInfo handleExistingReview(HttpServletRequest request, ExistingReviewException ex) {
//        LOG.error("Tried to add new review while existing review found! --> {}", ex.getExistingReview());
//        return new ErrorInfo(request.getRequestURL().toString(), ex);
//    }

}
