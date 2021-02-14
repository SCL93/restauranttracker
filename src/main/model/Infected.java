package model;

import java.util.ArrayList;
import java.util.Random;

public class Infected {

    // REQUIRES: Arraylist to not be empty
    // MODIFIES: this
    // EFFECTS: random generates number between 0 and array size,produces random number.
    public static int randomCovidSelect(ArrayList<Customer> todayCustomers) {
        Random rand = new Random();
        // random number made from 0 to size of todayCustomers array
        return rand.nextInt(todayCustomers.size());
    }

    // REQUIRES: Arraylist to not be empty, int given within size of array
    // EFFECTS: produces customer at array index of int provided with the todayCustomer arraylist
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

