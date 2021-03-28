package ui.tabs;

import ui.RestaurantApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomeTab extends Tab {
    private JLabel welcomeMessage;


    // MODIFIES: this
    // EFFECTS: construct Home tab
    public HomeTab(RestaurantApp controller) {
        super(controller);

        setLayout(new GridLayout(5, 5, 3, 20)); // adjust vertical gap later
        placeWelcomeMessage();
        placeAddCustomerTabButton();
        placeAnalyzeTabButton();
    }

    // MODIFIES: this
    // EFFECTS: creates and adds welcome message on panel
    private void placeWelcomeMessage() {
        welcomeMessage = new JLabel("Welcome, please choose an action", JLabel.CENTER);
        welcomeMessage.setSize(1, (2 / 3));
        this.add(welcomeMessage);

    }

    // MODIFIES: this
    // EFFECTS: creates and adds Add Customer Button to panel. Controller switch to AddCustomer tab on click.
    private void placeAddCustomerTabButton() {
        JButton addCustomerButton = new JButton("ADD CUSTOMER");
        this.add(addCustomerButton);

        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addCustomerButton) {
                    getController().getTabbedPane().setSelectedIndex(RestaurantApp.ADD_CUSTOMER_TAB_INDEX);
                }

            }
        });

    }

    // MODIFIES: this
    // EFFECTS: creates and adds Analyze Button to panel. Controller switch to Analyze tab on click.
    private void placeAnalyzeTabButton() {
        JButton analyzeButton = new JButton("ANALYZE");
        this.add(analyzeButton);

        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == analyzeButton) {
                    getController().getTabbedPane().setSelectedIndex(RestaurantApp.ANALYZE_TAB_INDEX);
                }

            }
        });

    }
}

