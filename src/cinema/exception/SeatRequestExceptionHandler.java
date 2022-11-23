package cinema.exception;

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

    @ExceptionHandler(value = {SeatPurchasedException.class, SeatInvalidException.class})
    protected ResponseEntity<Object> handleJochen(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                Map.of("error", ex.getMessage()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST,request);
    }


/*    @ExceptionHandler(SeatRequestException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public SeatException handleSeatRequestException(SeatRequestException e) {
        SeatException seatException = new SeatException(e.getError());
        return seatException;
    }*/
}

/*
class SeatException {
    private String error;

    public SeatException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}*/
