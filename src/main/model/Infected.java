package model;

import exception.EmptyListException;

import java.util.ArrayList;
import java.util.Random;

public class Infected {

    // MODIFIES: this
    // EFFECTS: random generates number between 0 and customer list size,produces random number.
    //          If customer list is empty, throws exception to notify user
    public static int randomCovidSelect(ArrayList<Customer> todayCustomers) throws EmptyListException {
        if (todayCustomers.size() == 0) {
            throw new EmptyListException("No Customers on List");
        }
        Random rand = new Random();
        // random number made from 0 to size of todayCustomers array
        return rand.nextInt(todayCustomers.size());
    }

    // EFFECTS: produces customer at array index of int provided with the todayCustomer arraylist
    //         note - ArrayList passed into this method will never be empty
    //                randomCovidSelect checks for this earlier.
    public static Customer covidSelectedCustomer(ArrayList<Customer> todayCustomer, int random) {
        return todayCustomer.get(random);
    }

    // EFFECTS: produces first name of customer given
    public static String covidSelectedName(Customer covidSelected) {
        return covidSelected.getFirstName();
    }

    // EFFECTS: produces int (0-23) representing hour of customer check in
    public static int covidSelectedTime(Customer covidSelected) {
        return covidSelected.getCheckInTime();
    }

}

