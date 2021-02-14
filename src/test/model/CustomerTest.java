package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer testCustomer1;


    @BeforeEach
    void runBefore() {
        testCustomer1 = new Customer("John", "Doe",
                "johndoe@gmail.com", "(604)333-3333", 1);
    }

    @Test
    void testConstructCustomer() {
        assertEquals("John", testCustomer1.getFirstName());
        assertEquals("Doe", testCustomer1.getLastName());
        assertEquals("johndoe@gmail.com", testCustomer1.getEmail());
        assertEquals("(604)333-3333", testCustomer1.getPhoneNumber());
        assertEquals(1, testCustomer1.getCheckInTime());
    }
}