package banking_atm.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;


@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiBadRequest(ApiRequestException e) {

        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))

        );

        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ApiRequestInternal.class})
    public ResponseEntity<Object> handleApiInternalServer(ApiRequestInternal e) {

        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Z"))

        );

        return new ResponseEntity<>(apiException,HttpStatus.INTERNAL_SERVER_ERROR);
    }
@ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException exception){

        ApiException apiException = new ApiException("Validation Error",HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("Z"))

        );

        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
}
