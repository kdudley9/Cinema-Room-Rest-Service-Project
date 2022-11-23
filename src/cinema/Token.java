package cinema;
import java.util.UUID;

public class Token {
    private String token;
    private Seats ticket;

    public Token() {}
    public Token(Seats ticket) {
        // Create a random token
        this.token = UUID.randomUUID().toString();
        this.ticket = ticket;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Seats getTicket() {
        return ticket;
    }

    public void setTicket(Seats ticket) {
        this.ticket = ticket;
    }
}
