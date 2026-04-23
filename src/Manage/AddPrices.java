package Manage;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddPrices extends JFrame {
    JPanel jPanel;
    JLabel jLabelPrices;
    JTextField jTextFieldPrices;
    JButton jButtonSubmit;
    public AddPrices() throws HeadlessException { this("Add Prices"); }
    public AddPrices(String title) throws HeadlessException { super(title); initializeApp(); }
    public void initializeApp() {
        this.setBounds(600, 100, 400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jPanel = new JPanel();
        jPanel.setLayout(new MigLayout());
        this.setContentPane(jPanel);

        jLabelPrices = new JLabel();
        jLabelPrices.setText("Add - Prices");
        jPanel.add(jLabelPrices, "align 50%, wrap");
        jTextFieldPrices = new JTextField();
        jPanel.add(jTextFieldPrices, "pushx, grow, wrap");

        jButtonSubmit = new JButton();
        jButtonSubmit.setText("Submit");
        jButtonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("src/File/prices.txt", true));
                    bw.write(jTextFieldPrices.getText());
                    bw.newLine();
                    bw.close();
                } catch (IOException i) { i.printStackTrace(); }
                JOptionPane.showMessageDialog(null, "Data Added Successfully \n - " + jTextFieldPrices.getText());
            }
        });
        jPanel.add(jButtonSubmit, "align 50%");
        this.setVisible(true);
    }
}