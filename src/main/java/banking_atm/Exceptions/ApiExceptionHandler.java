package banking_atm.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiBadRequest(ApiRequestException e) {

        ApiException apiException = new ApiException(
                e.getMessage(),
               e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z"))

        );

        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ApiRequestInternal.class})
    public ResponseEntity<Object> handleApiInternalServer(ApiRequestInternal e) {

        ApiException apiException = new ApiException(
                e.getMessage(),
                e.getLocalizedMessage(),
                ZonedDateTime.now(ZoneId.of("Z"))

        );

        return new ResponseEntity<>(apiException,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object>handleVallidation(MethodArgumentNotValidException e){
        ApiException apiException = new ApiException(
                "Validation Error",
                e.getBindingResult().getFieldError().getDefaultMessage(),
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        ApiException apiException = new ApiException(
                "Validation error",
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
