package Manage;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPrices extends JFrame {
    JPanel jPanel;
    JButton jButtonEditPrices;
    JLabel jLabelTitle, jLabelSelect, jLabelEdit;
    JTextField jTextFieldPrices;
    JList jListPrices;
    public EditPrices() throws HeadlessException { this("Edit Prices"); }
    public EditPrices(String title) throws HeadlessException { super(title); initializeApp(); }
    public void initializeApp() {
        this.setBounds(600, 100, 400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jPanel = new JPanel();
        jPanel.setLayout(new MigLayout());
        this.setContentPane(jPanel);

        jLabelTitle = new JLabel();
        jLabelTitle.setText("[ PRICES ] ");
        jPanel.add(jLabelTitle, "wrap, align 50%, pushx");

        jLabelSelect = new JLabel();
        jLabelSelect.setText("Select To Edit :");
        jPanel.add(jLabelSelect, "align 50% ,wrap");

        Data data = new Data();
        jListPrices = new JList(data.listDataPrices());
        jPanel.add(jListPrices, "align 50%, grow, wrap");

        jLabelEdit = new JLabel();
        jLabelEdit.setText("-- Edit --");
        jPanel.add(jLabelEdit, "align 50%, wrap");

        jTextFieldPrices = new JTextField();
        jPanel.add(jTextFieldPrices, "pushx, grow, wrap");

        jButtonEditPrices = new JButton();
        jButtonEditPrices.setText("Submit Edit");
        jButtonEditPrices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jListPrices.getSelectedValue() != null) {
                    String editData = jTextFieldPrices.getText();
                    data.replaceDataPrices(String.valueOf(jListPrices.getSelectedValue()), editData);
                } else {
                    JOptionPane.showMessageDialog(null, "Please Select To Edit !!");
                }
            }
        });
        jPanel.add(jButtonEditPrices, "align 50% 50%, center, pushx");
        this.setVisible(true);
    }
}