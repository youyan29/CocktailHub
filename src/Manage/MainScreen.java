package Manage;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JPanel {
    JPanel jPanel;
    JList jList;
    Data data; 
    JLabel jLabelDatabase, jLabelRecent, jLabelCocktails, jLabelFlavors, jLabelPrices;
    JButton jButtonEdit, jButtonDelete, jButtonAdd, jButtonReload, jButtonSortPrice, jButtonShowLastDeleted;

    public MainScreen() {
        initializeApp();
    }

    public void initializeApp() {

        this.setLayout(new BorderLayout());

        jPanel = new JPanel();
        jPanel.setLayout(new MigLayout());

        this.add(jPanel, BorderLayout.CENTER);

        /*
         * Author: Yen-Chia Chiu
         * Feature: Load data and UI blocks
         */
        data = new Data();
        
        jLabelDatabase = new JLabel("[ DATABASE COCKTAILS ]");
        jPanel.add(jLabelDatabase, "wrap");
        
        menuCocktails();
        menuFlavors();
        menuPrices();
        
        jLabelRecent = new JLabel("[ RECENT COCKTAILS ]");
        jPanel.add(jLabelRecent, "newline 25px, wrap");
        
        recentCocktails();

        /*
         * Author: Yen-Chia Chiu
         * Feature: The Button of Sort by Price: how to get the data and sort
         */
        jButtonSortPrice = new JButton("Sort by Price");
        jPanel.add(jButtonSortPrice, "align 50%, center, span, newline 10px");

        jButtonSortPrice.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] cocktails = data.getCocktailsArray();
                data.mergeSortByPrice(cocktails, 0, cocktails.length - 1);
                
                DefaultListModel<String> sortedModel = new DefaultListModel<>();
                for (String cocktail : cocktails) {
                    sortedModel.addElement(cocktail);
                }
                jList.setModel(sortedModel);				
			}
        	
        });
        
        /*
         * Author: Yen-Chia Chiu
         * Feature: Display the last deleted data in a pop-up window
         */
        jButtonShowLastDeleted = new JButton("Show Last Deleted");
        jPanel.add(jButtonShowLastDeleted, "align 50%, center, span, newline 10px");

        jButtonShowLastDeleted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String lastDeleted = data.peekLastDeleted();

                if (lastDeleted == null) {
                    JOptionPane.showMessageDialog(null, "No deleted record.");
                } else {
                    JOptionPane.showMessageDialog(null, "Last deleted: " + lastDeleted);
                }
            }
        });
        
        jButtonReload = new JButton("Reload / Refresh");
        jPanel.add(jButtonReload, "align center, span, wrap");

        jButtonReload.addActionListener(e -> {
            this.removeAll();
            initializeApp();
            this.revalidate();
            this.repaint();
        });
    }
    
    private void menuCocktails() {
        jLabelCocktails = new JLabel("Cocktails");
        jPanel.add(jLabelCocktails, "growx");

        jButtonEdit = new JButton("Edit");
        jButtonEdit.addActionListener(e -> new EditCocktails());
        jPanel.add(jButtonEdit);

        jButtonDelete = new JButton("Delete");
        jButtonDelete.addActionListener(e -> new DelCocktails());
        jPanel.add(jButtonDelete);

        jButtonAdd = new JButton("Add");
        jButtonAdd.addActionListener(e -> new AddCocktails());
        jPanel.add(jButtonAdd, "wrap");
    }

    private void menuFlavors() {
        jLabelFlavors = new JLabel("Flavors");
        jPanel.add(jLabelFlavors);

        jButtonEdit = new JButton("Edit");
        jButtonEdit.addActionListener(e -> new EditFlavors());
        jPanel.add(jButtonEdit);

        jButtonDelete = new JButton("Delete");
        jButtonDelete.addActionListener(e -> new DelFlavors());
        jPanel.add(jButtonDelete);

        jButtonAdd = new JButton("Add");
        jButtonAdd.addActionListener(e -> new AddFlavors());
        jPanel.add(jButtonAdd, "wrap");
    }

    private void menuPrices() {
        jLabelPrices = new JLabel("Prices");
        jPanel.add(jLabelPrices);

        jButtonEdit = new JButton("Edit");
        jButtonEdit.addActionListener(e -> new EditPrices());
        jPanel.add(jButtonEdit);

        jButtonDelete = new JButton("Delete");
        jButtonDelete.addActionListener(e -> new DelPrices());
        jPanel.add(jButtonDelete);

        jButtonAdd = new JButton("Add");
        jButtonAdd.addActionListener(e -> new AddPrices());
        jPanel.add(jButtonAdd, "wrap");
    }

    private void recentCocktails() {
        jList = new JList<>(data.listDataCocktails());
        jPanel.add(new JScrollPane(jList), "span, growx, wrap");
    }
}
