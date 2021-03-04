package persistence;

import model.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCustomerListArray(String firstname, String lastname, String email,
                                          String phoneNumber, int checkInTime, Customer customer){
        assertEquals(firstname,customer.getFirstName());
        assertEquals(lastname, customer.getLastName());
        assertEquals(email,customer.getEmail());
        assertEquals(phoneNumber,customer.getPhoneNumber());
        assertEquals(checkInTime,customer.getCheckInTime());
    }
}
