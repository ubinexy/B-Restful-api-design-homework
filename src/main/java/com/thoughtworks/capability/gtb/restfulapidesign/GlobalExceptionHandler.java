package com.thoughtworks.capability.gtb.restfulapidesign;

import com.thoughtworks.capability.gtb.restfulapidesign.Exceptions.StudentExistsException;
import com.thoughtworks.capability.gtb.restfulapidesign.Exceptions.StudentNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String ExceptionHandler(RuntimeException exception) {
        return "invalid request body.";
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String exceptionHandler(ConstraintViolationException exception) {
        return "id not exists.";
    }

    @ExceptionHandler({StudentNotExistsException.class, StudentExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String exceptionHandler(RuntimeException exception) {
        return exception.getMessage();
    }

}
