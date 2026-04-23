package Manage;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DelFlavors extends JFrame {
    JPanel jPanel;
    JList jListFlavors;
    JButton jButtonSubmit;
    JLabel jLabelDel, jLabelTitle;
    public DelFlavors() throws HeadlessException { this("Delete Flavors"); }
    public DelFlavors(String title) throws HeadlessException { super(title); initializeApp(); }
    public void initializeApp() {
        this.setBounds(600, 100, 400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jPanel = new JPanel();
        jPanel.setLayout(new MigLayout());
        this.setContentPane(jPanel);

        jLabelTitle = new JLabel();
        jLabelTitle.setText("[ FLAVORS ] ");
        jPanel.add(jLabelTitle, "wrap, align 50%, pushx");

        jLabelDel = new JLabel();
        jLabelDel.setText("Select To Delete:");
        jPanel.add(jLabelDel, "pushx ,wrap, align 50%");

        Data data = new Data();
        jListFlavors = new JList(data.listDataFlavors());
        jPanel.add(jListFlavors, "pushx, align 50%, grow, wrap");

        jButtonSubmit = new JButton();
        jButtonSubmit.setText("Submit Delete");
        jButtonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedList = jListFlavors.getSelectedValue();
                if (selectedList != null) {
                    data.delFlavors(String.valueOf(selectedList));
                } else {
                    JOptionPane.showMessageDialog(null, "Please Select To Delete !!");
                }
            }
        });
        jPanel.add(jButtonSubmit, "align 50%, center");
        this.setVisible(true);
    }
}