package OrderSystem;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.util.HashMap;

public class BillPanel extends JPanel {

	private JTextArea newArea;
	private JTextArea orderedArea;

	private JLabel taxLabel;
	private JLabel totalLabel;

	private HashMap<String, Item> cart;
	private HashMap<String, Item> currentOrder;

	public BillPanel(HashMap<String, Item> cart, HashMap<String, Item> currentOrder, CardLayout cardLayout,
			JPanel mainPanel, OrderingPanel orderingPanel) {

		this.cart = cart;
		this.currentOrder = currentOrder;
		setLayout(new MigLayout("fill", "[grow]", "[]15[120!][]10[grow][]"));

		JButton backBtn = new JButton("Back");
		add(backBtn, "pos 1al 0al");

		backBtn.addActionListener(e -> {
			cardLayout.show(mainPanel, "ordering");
		});

		add(new JLabel("New Order"), "wrap");
		newArea = new JTextArea();
		add(new JScrollPane(newArea), "grow, wrap");

		add(new JLabel("Ordered"), "wrap");
		orderedArea = new JTextArea();
		add(new JScrollPane(orderedArea), "grow, wrap");

		taxLabel = new JLabel("Tax: $0");
		totalLabel = new JLabel("Total: $0");

		add(taxLabel, "right, wrap");
		add(totalLabel, "right, wrap");

		JButton orderBtn = new JButton("Order");
		JButton recordBtn = new JButton("Record");

		add(orderBtn, "split 2, center");
		add(recordBtn);

		orderBtn.addActionListener(e -> {
			for (Item item : cart.values()) {

				if (currentOrder.containsKey(item.name)) {
					currentOrder.get(item.name).quantity += item.quantity;
				} else {
					Item copy = new Item(item.name, item.price, item.description);
					copy.quantity = item.quantity;
					currentOrder.put(copy.name, copy);
				}
			}

			cart.clear();

			for (Item item : orderingPanel.getMenu()) {
				item.quantity = 0;
			}

			orderingPanel.refreshUI();
			refresh();
			orderingPanel.resetToDefault();
		});

		recordBtn.addActionListener(e -> {

			if (currentOrder.isEmpty()) {

	            JOptionPane.showMessageDialog(
	                    null,
	                    "No orders have been placed.",
	                    "Note",
	                    JOptionPane.WARNING_MESSAGE
	            );

	            return; 
	        }
						
			
			BillSaver.save(currentOrder);

			currentOrder.clear();
			cart.clear();

			for (Item item : orderingPanel.getMenu()) {
				item.quantity = 0;
			}

			orderingPanel.refreshUI();
			refresh();

			cardLayout.show(mainPanel, "ordering");
		});
	}

	public void refresh() {

		newArea.setText("");
		orderedArea.setText("");

		double subtotal = 0;

		for (Item item : cart.values()) {
			double price = item.price * item.quantity;
			subtotal += price;
			newArea.append(item.name + " x" + item.quantity + " $" + price + "\n");
		}

		for (Item item : currentOrder.values()) {
			double price = item.price * item.quantity;
			subtotal += price;
			orderedArea.append(item.name + " x" + item.quantity + " $" + price + "\n");
		}

		double tax = subtotal * 0.0625;
		double total = subtotal + tax;

		taxLabel.setText("Tax (6.25%): $" + String.format("%.2f", tax));
		totalLabel.setText("Total: $" + String.format("%.2f", total));
	}
}