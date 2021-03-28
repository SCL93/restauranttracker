package ui.tabs;

import model.Alert;
import model.Customer;
import model.CustomerList;
import model.Infected;
import persistence.JsonReader;
import ui.RestaurantApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class AnalyzeTab extends Tab {


    private ArrayList<Customer> infectedToday;
    private String infectedCustomerName;
    private int infectedCustomerTime;
    private JScrollPane customerListPane;
    private JTextArea contactListText;
    private JTextArea customerListText;
    private CustomerList customerListJson;
    private final JsonReader jsonReader;
    private static final String FILE_LOCATION = "./data/customerList.json";


    public AnalyzeTab(RestaurantApp controller) {
        super(controller);
        jsonReader = new JsonReader(FILE_LOCATION);
        loadCustomerListFromJson();

        this.setLayout(new GridLayout(4, 1));
        placeAnalyzeWindow();
        contactListButton();

    }

    public void placeAnalyzeWindow() {
        JPanel panel = initializeAnalysisWindow();
        JButton b1 = new JButton("ANALYZE IF ANYONE GOT COVID");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    customerListText.setText("");
                    analysisName();
                    customerListText.append(infectedCustomerName + " got COVID-19!" + "\n" + "\n");
                    customerListText.append(infectedCustomerName + " was at the restaurant at " + infectedCustomerTime
                            + ". All customers in restaurant during " + infectedCustomerTime
                            + " must be alerted!");
                }
            }
        });
        customerListPane.setViewportView(customerListText);
        panel.add(customerListPane);
        panel.add(b1);
        this.add(panel);

    }

    public void contactListButton() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        contactListText = new JTextArea("", 50, 10);
        JButton b1 = new JButton("GENERATE CUSTOMERS NEEDED TO BE CONTACTED");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    contactListText.setText("");
                    contactListText.append("List of Customers we need to contact:" + "\n" + "\n");
                    for (Customer c : infectedToday) {
                        contactListText.append(c.getFirstName() + " " + c.getLastName() + " / " + "Email:"
                                + c.getEmail() + " / " + "Phone#:" + c.getPhoneNumber() + "\n");

                    }
                }
            }
        });
        panel.add(contactListText);
        panel.add(b1);
        this.add(panel);
    }

    public JPanel initializeAnalysisWindow() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        customerListPane = new JScrollPane(new JTextArea(50, 10));
        customerListText = new JTextArea("", 50, 10);
        customerListText.setVisible(true);
        return panel;
    }

    public void analysisName() {
        loadCustomerListFromJson();
        ArrayList<Customer> todayCustomers = customerListJson.getCustomerSoFar();
        int indexInfected = Infected.randomCovidSelect(todayCustomers);
        Customer infectedCustomer = Infected.covidSelectedCustomer(todayCustomers, indexInfected);
        infectedCustomerName = new String();
        infectedCustomerName = Infected.covidSelectedName(infectedCustomer);
        infectedCustomerTime = Infected.covidSelectedTime(infectedCustomer);
        infectedToday = Alert.othersInfected(infectedCustomerTime, todayCustomers);


    }

    public void loadCustomerListFromJson() {
        try {
            customerListJson = jsonReader.read();       /// LOADS SAVED INTO CUSTOMER LIST OBJECT
            System.out.println("Loaded saved CustomerList from JSON file");
        } catch (IOException e) {
            System.out.println("File not found, did not load file");
        }
    }


}


