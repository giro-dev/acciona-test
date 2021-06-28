package giro.albert.accionatest.app.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandles {

    @ExceptionHandler(value = { NoSuchElementException.class })
    public ResponseEntity<ErrorResponse> handleInvalidInputException(NoSuchElementException ex) {

        return new ResponseEntity<ErrorResponse>(
                ErrorResponse.builder().error(ex.getMessage()).build(),
                HttpStatus.NOT_FOUND);
    }
}
