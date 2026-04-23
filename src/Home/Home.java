package Home;

import javax.swing.*;

import Manage.MainScreen;
import OrderSystem.BillSystemPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class Home {
    public static void main(String[] args) {

    	Locale.setDefault(Locale.ENGLISH);
    	
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Machine not Learning");
            frame.setSize(400, 550);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            CardLayout cardLayout = new CardLayout();
            JPanel root = new JPanel(cardLayout);

            JPanel homePanel = new JPanel();
            homePanel.setLayout(new GridBagLayout());

            JPanel inner = new JPanel();
            inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));

            JLabel title = new JLabel("Machine not Learning");
            title.setFont(new Font("Arial", Font.BOLD, 26));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel name = new JLabel("CocktailHub");
            name.setFont(new Font("Arial", Font.BOLD, 22));
            name.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel describe = new JLabel("Cocktail Database System");
            describe.setFont(new Font("Arial", Font.BOLD, 18));
            describe.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton manageBtn = new JButton("Manage");
            JButton orderBtn = new JButton("Order");

            manageBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

            Dimension size = new Dimension(100, 40);

            manageBtn.setMaximumSize(size);
            orderBtn.setMaximumSize(size);
            
            inner.add(title);
            inner.add(Box.createVerticalStrut(50));
            inner.add(name);
            inner.add(Box.createVerticalStrut(10));
            inner.add(describe);
            inner.add(Box.createRigidArea(new Dimension(0, 30)));
            inner.add(manageBtn);
            inner.add(Box.createRigidArea(new Dimension(0, 10)));
            inner.add(orderBtn);

            homePanel.add(inner);


            JPanel managePanel = new JPanel(new BorderLayout());

            MainScreen mainScreen = new MainScreen();
            managePanel.add(mainScreen, BorderLayout.CENTER);

            JButton back1 = new JButton("Back");
            managePanel.add(back1, BorderLayout.NORTH);

            back1.addActionListener(e -> cardLayout.show(root, "home"));

            BillSystemPanel billSystem = new BillSystemPanel();

            JPanel orderWrapper = new JPanel(new BorderLayout());

            JButton back2 = new JButton("Back");
            orderWrapper.add(back2, BorderLayout.NORTH);
            orderWrapper.add(billSystem, BorderLayout.CENTER);

            back2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	billSystem.getOrderingPanel().resetToDefault();
                	cardLayout.show(root, "home");
                }
            });

            root.add(homePanel, "home");
            root.add(managePanel, "manage");
            root.add(orderWrapper, "order");

            manageBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	cardLayout.show(root, "manage");
                }
            });
            
            orderBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	billSystem.getOrderingPanel().resetToDefault();
                	cardLayout.show(root, "order");
                }
            });
            
            cardLayout.show(root, "home");

            frame.add(root);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}