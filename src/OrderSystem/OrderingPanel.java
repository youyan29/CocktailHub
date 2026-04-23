package OrderSystem;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderingPanel extends JPanel {

	private List<Item> menu;
	private HashMap<String, Item> cart;
	private boolean nameAsc = false;
	private boolean priceAsc = true;
	private JPanel listPanel;
	private JTextField searchField;

	public OrderingPanel(List<Item> menu, HashMap<String, Item> cart, CardLayout cardLayout, JPanel mainPanel) {

		this.menu = menu;
		this.cart = cart;

		setLayout(new MigLayout("fillx", "[grow]", "[][grow]"));

		searchField = new JTextField(15);
		JButton searchBtn = new JButton("Search");
		JButton resetBtn = new JButton("Reset");

		JPanel searchPanel = new JPanel(new MigLayout("ins 5, fillx", "[]10[]10[]push"));

		searchPanel.add(new JLabel("Search:"));
		searchPanel.add(searchField);
		searchPanel.add(searchBtn);
		searchPanel.add(resetBtn, "right");

		JButton sortNameBtn = new JButton("Sort Name");
		JButton sortPriceBtn = new JButton("Sort Price");
		JButton billBtn = new JButton("Bill");

		JPanel sortPanel = new JPanel(new MigLayout("ins 5, fillx", "[]10[]push[]"));

		sortPanel.add(sortNameBtn);
		sortPanel.add(sortPriceBtn);
		sortPanel.add(billBtn, "right");

		add(searchPanel, "growx, wrap");
		add(sortPanel, "growx, wrap");

		listPanel = new JPanel(new MigLayout("fillx", "[grow]", ""));

		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setBorder(null);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollPane, "grow, push");

		sortMenuByName(true);
		refreshUI();

		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = searchField.getText().trim();
				searchMenu(keyword);
			}
		});

		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetToDefault();
			}
		});

		sortNameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortMenuByName(nameAsc);
				nameAsc = !nameAsc;
				refreshUI();
			}
		});

		sortPriceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortMenuByPrice(priceAsc);
				priceAsc = !priceAsc;
				refreshUI();
			}
		});

		billBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (Component comp : mainPanel.getComponents()) {
					if (comp instanceof BillPanel) {
						((BillPanel) comp).refresh();
					}
				}

				cardLayout.show(mainPanel, "bill");
			}
		});
	}

	public List<Item> getMenu() {
		return menu;
	}

	public void refreshUI() {
		listPanel.removeAll();

		for (Item item : menu) {
			listPanel.add(createItemPanel(item), "growx, wrap");
		}

		listPanel.revalidate();
		listPanel.repaint();
	}

	private void searchMenu(String keyword) {

		List<Item> fullMenu = DataLoader.loadItems("src/File/cocktails.txt");

		List<Item> filtered = new ArrayList<>();

		keyword = keyword.toLowerCase();

		for (Item item : fullMenu) {

			String name = item.name.toLowerCase();
			String desc = item.description.toLowerCase();
			String price = String.valueOf(item.price);

			if (name.contains(keyword) || desc.contains(keyword) || price.contains(keyword)) {

				filtered.add(item);
			}
		}

		menu.clear();
		menu.addAll(filtered);

		refreshUI();
	}

	private void sortMenuByName(boolean asc) {
		MergeSort.sort(menu, new Comparator<Item>() {
			public int compare(Item a, Item b) {
				return asc ? a.name.compareTo(b.name) : b.name.compareTo(a.name);
			}
		});
	}

	private void sortMenuByPrice(boolean asc) {
		MergeSort.sort(menu, new Comparator<Item>() {
			public int compare(Item a, Item b) {
				return asc ? Double.compare(a.price, b.price) : Double.compare(b.price, a.price);
			}
		});
	}

	public void resetToDefault() {

		List<Item> newMenu = DataLoader.loadItems("src/File/cocktails.txt");

		menu.clear();
		menu.addAll(newMenu);

		sortMenuByName(true);

		nameAsc = false;
		priceAsc = true;

		searchField.setText("");
		refreshUI();
	}

	private JPanel createItemPanel(Item item) {

		JPanel panel = new JPanel(new MigLayout("fillx", "[grow][120!]"));
		panel.setBorder(null);
		panel.setOpaque(false);

		JLabel name = new JLabel(item.name + " ($" + item.price + ")");
		name.setCursor(new Cursor(Cursor.HAND_CURSOR));

		name.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, item.description, "Details", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JButton plus = new JButton("+");
		JButton minus = new JButton("-");
		JLabel count = new JLabel(String.valueOf(item.quantity));

		JPanel control = new JPanel(new MigLayout("ins 0", "[]5[]5[]"));
		control.setOpaque(false);

		control.add(minus);
		control.add(count);
		control.add(plus);

		plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item.quantity++;

				cart.computeIfAbsent(item.name, k -> new Item(item.name, item.price, item.description));

				cart.get(item.name).quantity++;

				refreshUI();
			}
		});

		minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (item.quantity > 0) {
					item.quantity--;

					Item cartItem = cart.get(item.name);

					if (cartItem != null) {
						cartItem.quantity--;

						if (cartItem.quantity == 0) {
							cart.remove(item.name);
						}
					}

					refreshUI();
				}
			}
		});

		panel.add(name, "growx");
		panel.add(control);

		return panel;
	}
}