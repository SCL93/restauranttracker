package model;


import org.json.JSONObject;
import persistence.Writable;

public class Customer implements Writable {

    // FIELDS
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final int checkInTime;


    // REQUIRES: time from 0-24, phone number 10 digits
    // EFFECTS: constructs customer with firstname, lastname, phone #, email, check-in time

    public Customer(String firstName, String lastName, String email, String phoneNumber, int checkInTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.checkInTime = checkInTime;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("firstName", firstName);
        json.put("lastName", lastName);
        json.put("email", email);
        json.put("phoneNumber", phoneNumber);
        json.put("checkInTime", checkInTime);
        return json;
    }

    //EFFECTS: returns firstName
    public String getFirstName() {
        return firstName;
    }

    //EFFECTS: returns lastName
    public String getLastName() {
        return lastName;
    }

    //EFFECTS: returns email
    public String getEmail() {
        return email;
    }

    //EFFECTS: returns email
    public String getPhoneNumber() {
        return phoneNumber;
    }

    //EFFECTS: returns checkInTime
    public int getCheckInTime() {
        return checkInTime;
    }
}
