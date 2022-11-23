package cinema;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int total_rows;
    private int total_columns;
    private List<Seats> available_seats;

    public Room(/*int total_rows, int total_columns*/) {
        // Rooms have nine rows and nine columns
        this.total_rows = 9;
        this.total_columns = 9;
        this.available_seats = new ArrayList<>();

        // Create available seats
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                available_seats.add(new Seats(i,j));
            }
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public List<Seats> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<Seats> available_seats) {
        this.available_seats = available_seats;
    }
}
