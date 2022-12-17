package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class SeatController {
    private ConcurrentHashMap<String, String> purchaseTickets = new ConcurrentHashMap<>();
    private final Room room = new Room();

    private static final int totalRows = 9;
    private static final int totalColumns = 9;
    private static final String adminPassword = "super_secret";

    // Return all available seats
    @GetMapping("/seats")
    public Room getSeats() {
        return room;
    }

    // Endpoint that allows customers to purchase tickets
    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTickets(@RequestBody Seats seat) {
        if (purchaseTickets.containsValue(seat.toString())) {
            return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        } else if (seat.getColumn() > totalColumns || seat.getColumn() < 1 || seat.getRow() < 1 || seat.getRow() > totalRows) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        } else {
            seat.setPrice();
            Token token = new Token(seat);
            purchaseTickets.put(token.getToken(), seat.toString());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
    }

    // Endpoint that allows customers to return tickets
    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Map<String, String> tokenMap) {
        String token = tokenMap.get("token");
        if (purchaseTickets.containsKey(token)) {
            String serializedSeat = purchaseTickets.get(token);
            purchaseTickets.remove(token);
            return new ResponseEntity<>(Map.of("returned_ticket", new Seats(serializedSeat)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint that returns statistics only available to those with the correct password
    @PostMapping("/stats")
    public ResponseEntity<?> statistics(@RequestParam(required = false) String password){
        if (password == null) {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }

        if (password.equals(adminPassword)) {
            return new ResponseEntity<>(
                Map.of( "current_income", getCurrentIncome(),
                        "number_of_purchased_tickets", purchaseTickets.size(),
                        "number_of_available_seats", getNumberOfAvailableSeats()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
    }

    private int getCurrentIncome() {
        int total = 0;
        for (String serializedTicket: purchaseTickets.values()) {
            Seats seat = new Seats(serializedTicket);
            total += seat.getPrice();
        }
        return total;
    }

    private int getNumberOfAvailableSeats() {
        return totalRows * totalColumns - purchaseTickets.size();
    }
}
