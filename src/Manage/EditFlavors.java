package Manage;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditFlavors extends JFrame {
    JPanel jPanel;
    JButton jButtonEditFlavors;
    JLabel jLabelTitle, jLabelSelect, jLabelEdit;
    JTextField jTextFieldFlavors;
    JList jListFlavors;
    public EditFlavors() throws HeadlessException { this("Edit Flavors"); }
    public EditFlavors(String title) throws HeadlessException { super(title); initializeApp(); }
    public void initializeApp() {
        this.setBounds(600, 100, 400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jPanel = new JPanel();
        jPanel.setLayout(new MigLayout());
        this.setContentPane(jPanel);

        jLabelTitle = new JLabel();
        jLabelTitle.setText("[ FLAVORS ] ");
        jPanel.add(jLabelTitle, "wrap, align 50%, pushx");

        jLabelSelect = new JLabel();
        jLabelSelect.setText("Select To Edit :");
        jPanel.add(jLabelSelect, "align 50% ,wrap");

        Data data = new Data();
        jListFlavors = new JList(data.listDataFlavors());
        jPanel.add(jListFlavors, "align 50%, grow, wrap");

        jLabelEdit = new JLabel();
        jLabelEdit.setText("-- Edit --");
        jPanel.add(jLabelEdit, "align 50%, wrap");

        jTextFieldFlavors = new JTextField();
        jPanel.add(jTextFieldFlavors, "pushx, grow, wrap");

        jButtonEditFlavors = new JButton();
        jButtonEditFlavors.setText("Submit Edit");
        jButtonEditFlavors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jListFlavors.getSelectedValue() != null) {
                    String editData = jTextFieldFlavors.getText();
                    data.replaceDataFlavors(String.valueOf(jListFlavors.getSelectedValue()), editData);
                } else {
                    JOptionPane.showMessageDialog(null, "Please Select To Edit !!");
                }
            }
        });
        jPanel.add(jButtonEditFlavors, "align 50% 50%, center, pushx");
        this.setVisible(true);
    }
}