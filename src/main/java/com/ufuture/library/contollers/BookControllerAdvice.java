package com.ufuture.library.contollers;

import com.ufuture.library.utill.BookNotCreatedException;
import com.ufuture.library.utill.BookNotFoundException;
import com.ufuture.library.utill.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BookNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "Book with this id wasn't found!",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BookNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
