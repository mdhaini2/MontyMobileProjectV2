package com.projectV1.uniProject.Exceptions;


import com.projectV1.uniProject.Utils.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleIdNotValidException(RoleInvalidException exception) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
        errorObject.setMessage(exception.getMessage());
        errorObject.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_ACCEPTABLE);

    }
}
