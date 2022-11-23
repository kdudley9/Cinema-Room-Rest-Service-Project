package cinema;
import java.util.Objects;
public class Seats {
    private int row;
    private int column;
    private boolean purchased;
    private int price;

    // Class constructor
    public Seats() {}
    public Seats(int row, int column) {
        this.row = row;
        this.column = column;
        this.purchased = false;
        setPrice();
    }

    public Seats(String serializedString) {
        System.out.println(serializedString);
        String[] result = serializedString.split("_");
        if (result.length > 1) {
            try{
                this.row = Integer.parseInt(result[0]);
                this.column = Integer.parseInt(result[1]);
                setPrice();
            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
            }
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Seats seat = (Seats) obj;
        return this.row == seat.row && this.column == seat.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.row, this.column);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        // Rows 5 - 9 are $8 and anything closer is $10
        return price;
    }

    public void setPrice() {
        this.price = row > 4 ? 8 : 10;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        return this.row + "_" + this.column;
    }
}
