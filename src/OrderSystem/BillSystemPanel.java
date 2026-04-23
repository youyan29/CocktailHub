package OrderSystem;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class BillSystemPanel extends JPanel {
	private OrderingPanel ordering;

	public BillSystemPanel() {

	    setLayout(new BorderLayout());

	    CardLayout cardLayout = new CardLayout();
	    JPanel mainPanel = new JPanel(cardLayout);

	    List<Item> menu = DataLoader.loadItems("src/File/cocktails.txt");

	    HashMap<String, Item> cart = new HashMap<>();
	    HashMap<String, Item> currentOrder = new HashMap<>();

	    ordering = new OrderingPanel(menu, cart, cardLayout, mainPanel);
	    BillPanel bill = new BillPanel(cart, currentOrder, cardLayout, mainPanel, ordering);

	    mainPanel.add(ordering, "ordering");
	    mainPanel.add(bill, "bill");

	    add(mainPanel, BorderLayout.CENTER);
	}
	
    
    public OrderingPanel getOrderingPanel() {
        return ordering;
    }
}