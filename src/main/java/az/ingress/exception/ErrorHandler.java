package az.ingress.exception;

import az.ingress.model.enums.ExceptionConstraints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static az.ingress.model.enums.ExceptionConstraints.INTERNAL_SERVER_EXCEPTION;
import static az.ingress.model.enums.ExceptionConstraints.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception e) {
        log.error("Exception", e);
        return new ErrorResponse(INTERNAL_SERVER_EXCEPTION.getCode(), INTERNAL_SERVER_EXCEPTION.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handle(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException", e);
        return new ErrorResponse(METHOD_NOT_ALLOWED.getCode(), METHOD_NOT_ALLOWED.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException e) {
        log.error("NotFoundException", e);
        return new ErrorResponse(e.getCode(), e.getMessage());
    }
}
