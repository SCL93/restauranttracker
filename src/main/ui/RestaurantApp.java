package ui;


import model.Customer;
import ui.tabs.AddCustomerTab;
import ui.tabs.AnalyzeTab;
import ui.tabs.HomeTab;
import javax.swing.*;
import java.util.*;

public class RestaurantApp extends JFrame {
    private static final String FILE_LOCATION = "./data/customerList.json";
    private Scanner input;
    public static final int FRAME_HEIGHT = 800;
    public static final int FRAME_WIDTH = 1000;
    public static final int HOME_TAB_INDEX = 0;
    public static final int ADD_CUSTOMER_TAB_INDEX = 1;
    public static final int ANALYZE_TAB_INDEX = 2;
    private final JTabbedPane topTabs;

    // MODIFIES: this
    // EFFECTS: starts the Restaurant application
    public RestaurantApp() {
        super("The COVID-19 RESTAURANT");
        topTabs = new JTabbedPane();// generate JFrame tab pane (overall window)
        topTabs.setSize(FRAME_WIDTH, FRAME_HEIGHT); // set size of overall window, default tabs at TOP
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // if multiple JFrame, only close this one.

        createJTabPaneTab();     // creates all the tabs and adds to JTabbedPane object
        add(topTabs);            //
        setVisible(true);

        Date today = Calendar.getInstance().getTime();
        System.out.println("------------- THE COVID-19 RESTAURANT -------------");
        System.out.println("------- Today is " + today + "--------");
        System.out.println("   ");
        startRestaurant();
    }

    // MODIFIES: this
    // EFFECTS: creates and adds home/add customer/analyze tabs to topTabs JTabbedPane
    private void createJTabPaneTab() {
        JPanel homeTab = new HomeTab(this);
        JPanel addCustomerTab = new AddCustomerTab(this);
        JPanel analyzeTab = new AnalyzeTab(this);

        topTabs.add(homeTab, HOME_TAB_INDEX);
        topTabs.setTitleAt(HOME_TAB_INDEX, "HOME");

        topTabs.add(addCustomerTab, ADD_CUSTOMER_TAB_INDEX);
        topTabs.setTitleAt(ADD_CUSTOMER_TAB_INDEX, "ADD CUSTOMER");

        topTabs.add(analyzeTab, ANALYZE_TAB_INDEX);
        topTabs.setTitleAt(ANALYZE_TAB_INDEX, "ANALYZE");
        add(topTabs); // adds the modified topTabs (a JTabbedFrame object) to the container

    }


    // EFFECTS: returns topTaps of the JTabbedPane
    public JTabbedPane getTabbedPane() {
        return topTabs;
    }

    // MODIFIES: this
    // EFFECTS: starts day at restaurant, process user input in console
    public void startRestaurant() {
        String userInput;
        openingInterface();
        input = new Scanner(System.in);
        userInput = input.next();
        userInput = userInput.toUpperCase();
        processInputOpen(userInput);
    }

    // MODIFIES: this
    // EFFECTS: closes day at restaurant, process user input in console
    public void closeRestaurant() {
        boolean notClosed = true;
        String userInput;

        while (notClosed) {
            closingInterface();
            input = new Scanner(System.in);
            userInput = input.next();
            userInput = userInput.toUpperCase();
            if (userInput.equals("Q")) {
                notClosed = false;
//            } else {
//                processInputClose(userInput);
            }
        }
        System.out.println("\n Goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user input for opening menu
    public void processInputOpen(String userInput) {
        if (userInput.equals("N")) {
            addNewCustomer();
            startRestaurant();
//        } else if (userInput.equals("S")) {
//            saveCustomerListJson();
            startRestaurant();
        } else if (userInput.equals("E")) {
            closeRestaurant();
        } else {
            System.out.println("Input is not valid, please select N or E");
            startRestaurant();
        }
    }

//    // MODIFIES: this
//    // EFFECTS: processes user input for closing menu
//    public void processInputClose(String userInput) {
//        if (userInput.equals("A")) {
//            startCovidAnalysis();
//        } else if (userInput.equals("L")) {
//            loadCustomerListFromJson();
//        } else {
//            System.out.println("Input is not valid, please select N or E");
//        }
//    }

    // EFFECTS: display OPENING menu and options
    public void openingInterface() {
        System.out.println("Please Choose:");
        System.out.println("\tN -> Add NEW customer");
//        System.out.println("\tL -> LOAD customer list from file");
        System.out.println("\tS -> SAVE customer list to file");
        System.out.println("\tE -> END day, Analyze and View COVID-19 transmission");
    }

    // EFFECTS: display CLOSING menu and options
    public void closingInterface() {
        System.out.println("------------Restaurant is now Closed------------");
        System.out.println("Please Choose:");
        System.out.println("\tL -> LOAD customer list from file");
        System.out.println("\tA -> Randomize and Analyze COVID Transmission");
        System.out.println("\tQ -> Quit");
    }


    // MODIFIES: this
    // EFFECTS: Initializes customer and adds to arraylist with user input details
    public void addNewCustomer() {
        System.out.println("------------Welcome to the COVID-19 Restaurant-----------");
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter your first name");
        String firstName = userInput.nextLine();
        System.out.println("Please enter your last name");
        String lastName = userInput.nextLine();
        System.out.println("Please enter your email");
        String email = userInput.nextLine();
        System.out.println("Please enter your phone number");
        String phoneNumber = userInput.nextLine();
        System.out.println("------------- Please follow your server to be seated --------------");
        System.out.println(" ");

        //Create calendar instance during time of Customer input in loop
        Calendar timeNow = Calendar.getInstance();
        int hourNow = timeNow.get(Calendar.HOUR_OF_DAY);

        //Create new Customer Object and add into array with above info
        Customer customer = new Customer(firstName, lastName, email, phoneNumber, hourNow);
//
//        // ADD new customer Object to CustomerList object
//        customerListJson.addCustomerToList(customer);
//
//        //prints Customer list to show how many people booked so far today
//        System.out.println("------TODAY'S CUSTOMER'S SO FAR------------");
//        for (Customer c : customerListJson.getCustomerSoFar()) {
//            System.out.println(c.getFirstName() + " " + c.getLastName());
//        }
//        System.out.println(" ");
    }

//    // EFFECTS: save the customerListJson to file
//    private void saveCustomerListJson() {
//        try {
//            jsonWriter.startWriting();
//            jsonWriter.write(customerListJson);
//            jsonWriter.closeWriting();
//            System.out.println("Customer List has been saved to:" + FILE_LOCATION);
//            System.out.println(" ");
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found, unable to write and save to: " + FILE_LOCATION);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads customerList from save file (replaces current customerList!)
//    private void loadCustomerListFromJson() {
//        try {
//            customerListJson = jsonReader.read();       /// LOADS SAVED INTO CUSTOMER LIST OBJECT
//            System.out.println("Loaded saved CustomerList from JSON file");
//        } catch (IOException e) {
//            System.out.println("File not found, did not load file");
//        }
//    }


    // EFFECTS: displays and starts EndOfDay protocol
//    public void startCovidAnalysis() {
//        System.out.println("-------------Begin COVID Analysis----------------");
//        System.out.println(" ");
//
//        // extract ARRAYLIST (element of CustomerList) from CustomerList Object
//        ArrayList<Customer> todayCustomers = customerListJson.getCustomerSoFar();
//        // randomly select person to get COVID
//        int indexInfected = Infected.randomCovidSelect(todayCustomers);
//        System.out.println("Today's unlucky number is...." + indexInfected + "!");
//        System.out.println(" ");
//        Customer infectedCustomer = Infected.covidSelectedCustomer(todayCustomers, indexInfected);
//        String infectedCustomerName = Infected.covidSelectedName(infectedCustomer);
//
//        //print randomly selected person name
//        System.out.println("Oh no! Looks like " + infectedCustomerName + " got COVID-19!");
//        System.out.println(" ");
//        int infectedCheckInTime = Infected.covidSelectedTime(infectedCustomer);
//        System.out.println("--- Customers in the restaurant during " + infectedCheckInTime
//                + ":00h" + " need " + "to be contacted---");
//        System.out.println(" ");
//        System.out.println("------------Full list of Customers we need to contact------------");
//
//        // filters for customers in array that may have come into contact with infected customer
//        ArrayList<Customer> infectedToday = Alert.othersInfected(infectedCheckInTime, todayCustomers);
//        printAll(infectedToday);
//    }
//
//    // REQUIRES: infectedToday Arraylist not empty
//    // MODIFIES: this
//    // EFFECTS: prints out all info of infected customers
//    public void printAll(ArrayList<Customer> infectedToday) {
//        for (Customer c : infectedToday) {
//            System.out.println(c.getFirstName() + " " + c.getLastName() + " / " + "Email:" + c.getEmail()
//                    + " / " + "Phone#:" + c.getPhoneNumber());
//        }
//        System.out.println(" ");
//        System.out.println(" ");
//    }
}
