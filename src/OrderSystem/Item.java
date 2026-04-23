package OrderSystem;
public class Item {
    String name;
    double price;
    int quantity;
    String description;

    public Item(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = 0;
    }
}