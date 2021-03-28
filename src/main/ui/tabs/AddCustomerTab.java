package ui.tabs;

import model.Customer;
import model.CustomerList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.RestaurantApp;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class AddCustomerTab extends Tab {
    private JScrollPane customerListPane;
    private JTextArea customerListText;
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

        Calendar timeNow = Calendar.getInstance();
        int month = timeNow.get(Calendar.MONTH);
        int day = timeNow.get(Calendar.DATE);
        int year = timeNow.get(Calendar.YEAR);
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
        placeCustomerListLabel();
    }

    public void addLabelInputFields(String type, JTextField inputField) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
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
                    Calendar timeNow = Calendar.getInstance();
                    int hourNow = timeNow.get(Calendar.HOUR_OF_DAY);
                    Customer customer = new Customer(firstName, lastName, email, phoneNumber, hourNow);
                    customerListJson.addCustomerToList(customer);
                    clearAllTextField();

                }
            }
        });
    }

    private void clearAllTextField() {
        firstNameField.setText("");
        lastNameField.setText("");
        phoneNumberField.setText("");
        emailField.setText("");
    }

    private void placeLoadButton() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton loadButton = new JButton("LOAD");
        panel.add(loadButton);
        this.add(panel);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loadButton) {
                    try {
                        loadCustomerListFromJson();
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }
//                    welcomeMessage.setText("CUSTOMER LIST LOADED FROM FILE");
                }

            }
        });

    }

    private void placeSaveButton() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton saveButton = new JButton("SAVE");
        panel.add(saveButton);
        this.add(panel);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == saveButton) {
                    saveCustomerListJson();
                    try {
                        playBells();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }

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
    public void loadCustomerListFromJson() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            customerListJson = jsonReader.read();       /// LOADS SAVED INTO CUSTOMER LIST OBJECT
            System.out.println("Loaded saved CustomerList from JSON file");
        } catch (IOException e) {
            System.out.println("File not found, did not load file");
        }
        playBells();
    }

    public void placeAllCustomerTextBox() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        customerListPane = new JScrollPane(new JTextArea(10, 40));
        customerListText = new JTextArea("", 10, 40);
        customerListText.setVisible(true);

        JButton b1 = new JButton("Update");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    customerListText.setText("");
                    for (Customer c : customerListJson.getCustomerSoFar()) {
                        customerListText.append(c.getFirstName() + " " + c.getLastName() + "\n");
                    }
                }
            }
        });
        customerListPane.setViewportView(customerListText);
        panel.add(customerListPane);
        panel.add(b1);
        this.add(panel);

    }

    public void placeCustomerListLabel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Current Customer List");
        panel.add(label);
        this.add(panel);
    }

    public void playBells() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        String bellSound = "bell.wav";
        AudioInputStream bell = AudioSystem.getAudioInputStream(new File(bellSound).getAbsoluteFile());
        Clip bellClip = AudioSystem.getClip();
        bellClip.open(bell);
        bellClip.start();


    }
}

