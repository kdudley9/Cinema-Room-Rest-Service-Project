package exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@RestControllerAdvice
public class SeatRequestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {SeatPurchasedException.class, SeatInvalidException.class, WrongTokenException.class})
    protected ResponseEntity<Object> handleJochen(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                Map.of("error", ex.getMessage()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
