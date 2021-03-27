package ui.tabs;

import model.Customer;
import model.CustomerList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.RestaurantApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class AddCustomerTab extends Tab {
    private JTextField firstNameField = new JTextField();
    private JTextField lastNameField = new JTextField();
    private JTextField phoneNumberField = new JTextField();
    private JTextField emailField = new JTextField();
    protected CustomerList customerListJson;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private static final String FILE_LOCATION = "./data/customerList.json";


    public AddCustomerTab(RestaurantApp controller) {
        super(controller);
        customerListJson = new CustomerList(day + "/" + month + "/" + year);
        jsonWriter = new JsonWriter(FILE_LOCATION);
        jsonReader = new JsonReader(FILE_LOCATION);
        this.setLayout(new GridLayout(10, 1, 1, 1));
        addLabelInputFields("First Name", firstNameField);
        addLabelInputFields("Last Name", lastNameField);
        addLabelInputFields("Phone Number", phoneNumberField);
        addLabelInputFields("Email", emailField);
        placeAddCustomerButton();
        placeLoadButton();
        placeSaveButton();
        placeAllCustomerTextBox();

        JTextField text1 = new JTextField();
        this.add(text1);

    }

    public void addLabelInputFields(String type, JTextField inputField) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel(type);
        inputField.setColumns(15);
        panel.add(label);
        panel.add(inputField);
        this.add(panel);

    }

    public void placeAddCustomerButton() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton addCustomerButton = new JButton("Add Customer");
        panel.add(addCustomerButton);
        this.add(panel);


        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addCustomerButton) {

                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String phoneNumber = phoneNumberField.getText();
                    String email = emailField.getText();
                    int hourNow = timeNow.get(Calendar.HOUR_OF_DAY);
                    Customer customer = new Customer(firstName, lastName, email, phoneNumber, hourNow);
                    customerListJson.addCustomerToList(customer);

                }
            }
        });


    }

    public void placeAllCustomerTextBox() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JTextArea customerField = new JTextArea();
        for (Customer c : customerListJson.getCustomerSoFar()) {
            customerField.append(c.getFirstName() + " " + c.getLastName());
        }
        this.add(panel);
    }

    private void placeLoadButton() {
        JButton loadButton = new JButton("LOAD");
        this.add(loadButton);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loadButton) {
                    loadCustomerListFromJson();
//                    welcomeMessage.setText("CUSTOMER LIST LOADED FROM FILE");
                }

            }
        });

    }

    private void placeSaveButton() {
        JButton saveButton = new JButton("SAVE");
        this.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == saveButton) {
                    saveCustomerListJson();
//                    welcomeMessage.setText("CUSTOMER LIST SAVED TO FILE");
                }

            }
        });

    }

    // EFFECTS: save the customerListJson to file
    public void saveCustomerListJson() {
        try {
            jsonWriter.startWriting();
            jsonWriter.write(customerListJson);
            jsonWriter.closeWriting();
            System.out.println("Customer List has been saved to:" + FILE_LOCATION);
            System.out.println(" ");
        } catch (FileNotFoundException e) {
            System.out.println("File not found, unable to write and save to: " + FILE_LOCATION);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads customerList from save file (replaces current customerList!)
    public void loadCustomerListFromJson() {
        try {
            customerListJson = jsonReader.read();       /// LOADS SAVED INTO CUSTOMER LIST OBJECT
            System.out.println("Loaded saved CustomerList from JSON file");
        } catch (IOException e) {
            System.out.println("File not found, did not load file");
        }
    }


}


//        JPanel panel1 = new JPanel();
//        panel1.setLayout(new FlowLayout());
//        JLabel name = new JLabel("First name");
//        JTextField textField = new JTextField(15);
//        panel1.add(name);
//        panel1.add(textField);
//        this.add(panel1);

//        JPanel panel2 = new JPanel();
//        panel2.setLayout(new FlowLayout());
//        JLabel name1 = new JLabel("last name");
//        JTextField textField1 = new JTextField(15);
//        panel2.add(name1);
//        panel2.add(textField1);
//        this.add(panel2);
