package Manage;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCocktails extends JFrame {
    JPanel jPanel;
    JLabel jLabelCocktail, jLabelIngredient, jLabelFlavors, jLabelPrices;
    JTextField jTextFieldCocktail, jTextFieldIngredient;
    JButton jButtonSubmit;
    JList jListPrices, jListFlavors;

    public AddCocktails() throws HeadlessException {
        this("Add Cocktails");
    }
    public AddCocktails(String title) throws HeadlessException {
        super(title);
        initializeApp();
    }
    public void initializeApp() {
        this.setBounds(600, 100, 400, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        jPanel = new JPanel();
        jPanel.setLayout(new MigLayout());
        this.setContentPane(jPanel);

        jLabelCocktail = new JLabel();
        jLabelCocktail.setText("COCKTAILS :");
        jPanel.add(jLabelCocktail, "wrap, align 50%");
        jTextFieldCocktail = new JTextField();
        jPanel.add(jTextFieldCocktail, "pushx, grow, wrap");

        jLabelIngredient = new JLabel();
        jLabelIngredient.setText("Ingredients:");
        jPanel.add(jLabelIngredient, "align 50%, wrap");
        jTextFieldIngredient = new JTextField();
        jPanel.add(jTextFieldIngredient, "pushx, grow, wrap");

        jLabelPrices = new JLabel();
        jLabelPrices.setText("Prices :");
        jPanel.add(jLabelPrices, "align 50%, wrap");
        Data data = new Data();
        jListPrices = new JList(data.listDataPrices());
        jPanel.add(jListPrices, "align 50%, center, grow, wrap");

        jLabelFlavors = new JLabel();
        jLabelFlavors.setText("Flavors :");
        jPanel.add(jLabelFlavors, "align 50%, wrap");
        jListFlavors = new JList(data.listDataFlavors());
        jPanel.add(jListFlavors, "align 50%, center, grow, wrap");

        jButtonSubmit = new JButton();
        jButtonSubmit.setText("Submit");
        jButtonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object checkFlavorObj = jListFlavors.getSelectedValue();
                Object checkPriceObj = jListPrices.getSelectedValue();
                String ingredient = jTextFieldIngredient.getText();
                String name = jTextFieldCocktail.getText();

                if (!ingredient.isEmpty() && checkFlavorObj != null && checkPriceObj != null) {
                    data.addCocktails(ingredient, name, String.valueOf(checkFlavorObj), String.valueOf(checkPriceObj));
                } else {
                    JOptionPane.showMessageDialog(null, "Add Cocktail Failed !!\n - Try Again");
                }
            }
        });
        jPanel.add(jButtonSubmit, "align 50%");
        this.setVisible(true);
    }
}
