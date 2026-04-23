package OrderSystem;
import java.util.HashMap;

public class Order {
    HashMap<String, Item> items;
    double total;

    public Order(HashMap<String, Item> items, double total) {
        this.items = new HashMap<>(items);
        this.total = total;
    }
}