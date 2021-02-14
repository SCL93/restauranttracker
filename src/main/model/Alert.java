package model;

import java.util.ArrayList;

public class Alert {

    // this class takes in the covid positive customer check-in time and customer array list, filtering for customers
    // who have visited the restaurant within +/- 1 hour of covid positive customer time.

    // REQUIRES: Arraylist<Customer> to not be empty
    // MODIFIES: this
    // EFFECTS: checks entire customer list with "contagious" time, produces list of customers potentially infected.

    public static ArrayList<Customer> othersInfected(int time, ArrayList<Customer> todayList) {
        ArrayList<Customer> covidList = new ArrayList<>();
        // assumption: if customer checked in either same time, or 1 hour before/after contagious = potentially infected
        for (Customer c : todayList) {
            if ((c.getCheckInTime() == time) || (c.getCheckInTime() == time + 1) || (c.getCheckInTime() == time - 1)) {
                covidList.add(c);
            }
        }
        return covidList;
    }
}
