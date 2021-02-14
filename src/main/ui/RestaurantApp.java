package ui;

import model.Alert;
import model.Customer;
import model.Infected;

import java.util.*;
import java.util.Random;

public class RestaurantApp {
    private Scanner input;
    private ArrayList<Customer> todayCustomers = new ArrayList<>();


    // EFFECTS: starts the Restaurant application
    public RestaurantApp() {
        startRestaurant();
    }

    // MODIFIES: this
    // EFFECTS: starts day at restaurant, process user input in console
    public void startRestaurant() {
        boolean workingHours = true;  //
        String userInput;

        while (workingHours) {
            openingInterface();
            input = new Scanner(System.in);
            userInput = input.next();
            userInput = userInput.toUpperCase();
            if (userInput.equals("E")) {
                workingHours = false;
            } else {
                processInput(userInput);
            }
        }

        startEndOfDay();

    }

    // MODIFIES: this
    // EFFECTS: processes user input for adding new customer
    public void processInput(String userInput) {
        if (userInput.equals("N")) {
            addNewCustomer();
        } else {
            System.out.println("Input is not valid, please select N or E");
        }
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
        System.out.println(" ");

        //Create calendar instance during time of Customer input in loop
        Calendar timeNow = Calendar.getInstance();
        int hourNow = timeNow.get(Calendar.HOUR_OF_DAY);

        //Create new Customer Object and add into array with above info
        Customer customer = new Customer(firstName, lastName, email, phoneNumber, hourNow);
        todayCustomers.add(customer);

        //prints Customer list to show many many people booked so far today
        System.out.println("------TODAY'S CUSTOMER'S SO FAR------------");
        for (Customer c : todayCustomers) {
            System.out.println(c.getFirstName() + " " + c.getLastName());
        }
        System.out.println(" ");
        System.out.println(" ");
    }


    // EFFECTS: display OPENING menu and options
    public void openingInterface() {
        Date today = Calendar.getInstance().getTime();
        System.out.println("------------- THE COVID-19 RESTAURANT -------------");
        System.out.println("------- Today is " + today + "--------");
        System.out.println("   ");
        System.out.println("Please Choose:");
        System.out.println("\tN -> Add NEW customer");
        System.out.println("\tE -> END day and Analyze COVID-19 transmission");
    }

    // EFFECTS: displays and starts EndOfDay protocol
    public void startEndOfDay() {
        System.out.println("------------Restaurant is now Closed------------");
        System.out.println(" ");
        System.out.println("-------------Begin COVID Analysis----------------");
        System.out.println(" ");
        // randomly select person to get COVID
        int indexInfected = Infected.randomCovidSelect(todayCustomers);
        System.out.println("Today's unlucky number is...." + indexInfected + "!");
        System.out.println(" ");
        Customer infectedCustomer = Infected.covidSelectedCustomer(todayCustomers, indexInfected);
        String infectedCustomerName = Infected.covidSelectedName(infectedCustomer);
        System.out.println("Oh no! Looks like " + infectedCustomerName + " got COVID-19!");
        System.out.println(" ");
        int infectedCheckInTime = Infected.covidSelectedTime(infectedCustomer);
        System.out.println("--- Customers in the restaurant during " + infectedCheckInTime
                + ":00h" + " need " + "to be contacted---");
        System.out.println(" ");
        System.out.println("------------Full list of Customers we need to contact------------");
        ArrayList<Customer> infectedToday = Alert.othersInfected(infectedCheckInTime, todayCustomers);
        printAll(infectedToday);
    }

    // REQUIRES: infectedToday Arraylist not empty
    // MODIFIES: this
    // EFFECTS: prints out all info of infected customers
    public void printAll(ArrayList<Customer> infectedToday) {
        for (Customer c : infectedToday) {
            System.out.println(c.getFirstName() + " " + c.getLastName() + " / " + "Email:" + c.getEmail()
                    + " / " + "Phone#:" + c.getPhoneNumber());
        }
    }
}
