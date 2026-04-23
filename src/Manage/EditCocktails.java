package Manage;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCocktails extends JFrame {
    JPanel jPanel;
    JButton jButtonEditCocktails;
    JLabel jLabelTitle, jLabelSelect, jLabelEdit, jLabelIngredient, jLabelFlavors, jLabelPrices, jLabelCocktails;
    JTextField jTextFieldCocktails, jTextFieldIngredient;
    JList jListCocktails, jListPrices, jListFlavors;

    public EditCocktails() throws HeadlessException { this("Edit Cocktails"); }
    public EditCocktails(String title) throws HeadlessException { super(title); initializeApp(); }
    public void initializeApp() {
        this.setBounds(600, 100, 400, 700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jPanel = new JPanel();
        jPanel.setLayout(new MigLayout());
        this.setContentPane(jPanel);

        jLabelTitle = new JLabel();
        jLabelTitle.setText("[ EDIT COCKTAILS ] ");
        jPanel.add(jLabelTitle, "wrap, align 50%, span");

        jLabelSelect = new JLabel();
        jLabelSelect.setText("Select To Edit :");
        jPanel.add(jLabelSelect, "align 50% ,wrap, newline 10px, span");

        Data data = new Data();
        jListCocktails = new JList(data.listDataCocktails());
        jPanel.add(jListCocktails, "center, grow, wrap, span");

        jLabelEdit = new JLabel();
        jLabelEdit.setText("---- EDIT ----");
        jPanel.add(jLabelEdit, "center, wrap, newline 15px, span");

        jLabelCocktails = new JLabel();
        jLabelCocktails.setText("Cocktail Name:");
        jPanel.add(jLabelCocktails, "align 50%, span, wrap");
        jTextFieldCocktails = new JTextField();
        jPanel.add(jTextFieldCocktails, "grow, span, wrap");

        jLabelIngredient = new JLabel();
        jLabelIngredient.setText("Ingredients:");
        jPanel.add(jLabelIngredient, "center, spanx, wrap");
        jTextFieldIngredient = new JTextField();
        jPanel.add(jTextFieldIngredient, "grow, span, wrap");

        jLabelPrices = new JLabel();
        jLabelPrices.setText("Prices:");
        jPanel.add(jLabelPrices);

        jLabelFlavors = new JLabel();
        jLabelFlavors.setText("Flavors:");
        jPanel.add(jLabelFlavors, "center, wrap");

        jListPrices = new JList(data.listDataPrices());
        jPanel.add(jListPrices);

        jListFlavors = new JList(data.listDataFlavors());
        jPanel.add(jListFlavors, "center, wrap");

        jButtonEditCocktails = new JButton();
        jButtonEditCocktails.setText("Submit Edit");
        jButtonEditCocktails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selCocktail = jListCocktails.getSelectedValue();
                String newName = jTextFieldCocktails.getText();
                Object newPrice = jListPrices.getSelectedValue();
                Object newFlavor = jListFlavors.getSelectedValue();
                String newIngredient = jTextFieldIngredient.getText();

                if (!newIngredient.isEmpty() && selCocktail != null && newPrice != null && newFlavor != null) {
                    data.replaceDataCocktails(String.valueOf(selCocktail), newIngredient, newName, String.valueOf(newPrice), String.valueOf(newFlavor));
                } else {
                    JOptionPane.showMessageDialog(null, "Please Select To Edit !!");
                }
            }
        });
        jPanel.add(jButtonEditCocktails, "align 70% 50%, pushx");
        this.setVisible(true);
    }
}