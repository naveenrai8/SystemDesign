package org.naveen.springbootcrud.controlleradvice;

import org.naveen.springbootcrud.dao.StudentErrorResponse;
import org.naveen.springbootcrud.exceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MiddlewareExceptionHandler extends RuntimeException{
    /*
    method parameter catches any exception which is super of exception passed as parameter
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex){
        return new ResponseEntity<>(new StudentErrorResponse(ex.toString(), HttpStatus.NOT_FOUND.toString()),
                HttpStatus.FOUND);
    }

    /*
    This acts as catch all exception for this controller
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex){
        return new ResponseEntity<>(new StudentErrorResponse(ex.toString(), HttpStatus.NOT_FOUND.toString()),
                HttpStatus.FOUND);
    }
}
