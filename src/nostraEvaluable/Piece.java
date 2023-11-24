package ae01;
public class Piece {
    private String type;
    private int quantity;

    public Piece(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }
    
    @Override
    public String toString() {
        return type + " - Quantity: " + quantity;
    }
}
