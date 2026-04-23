package Manage;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddFlavors extends JFrame {
    JPanel jPanel;
    JLabel jLabelFlavors;
    JTextField jTextFieldFlavors;
    JButton jButtonSubmit;
    public AddFlavors() throws HeadlessException { this("Add Flavors"); }
    public AddFlavors(String title) throws HeadlessException { super(title); initializeApp(); }
    public void initializeApp() {
        this.setBounds(600, 100, 400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jPanel = new JPanel();
        jPanel.setLayout(new MigLayout());
        this.setContentPane(jPanel);

        jLabelFlavors = new JLabel();
        jLabelFlavors.setText("Add - Flavors");
        jPanel.add(jLabelFlavors, "align 50%, wrap");
        jTextFieldFlavors = new JTextField();
        jPanel.add(jTextFieldFlavors, "pushx, grow, wrap");

        jButtonSubmit = new JButton();
        jButtonSubmit.setText("Submit");
        jButtonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("src/File/flavors.txt", true));
                    bw.write(jTextFieldFlavors.getText());
                    bw.newLine();
                    bw.close();
                } catch (IOException i) { i.printStackTrace(); }
                JOptionPane.showMessageDialog(null, "Data Added Successfully \n - " + jTextFieldFlavors.getText());
            }
        });
        jPanel.add(jButtonSubmit, "align 50%");
        this.setVisible(true);
    }
}