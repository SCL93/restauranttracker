package model;

import org.json.JSONObject;
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

    @Test
    void testToJsonObject(){
        JSONObject jsonActual= new JSONObject();
        jsonActual.put("firstName", "John");
        jsonActual.put("lastName", "Doe");
        jsonActual.put("email", "johndoe@gmail.com");
        jsonActual.put("phoneNumber","(604)333-3333");
        jsonActual.put ("checkInTime", 1);
        // to.String method needed, as two JSONObjects with same content
        // are considered different in assertEquals. to.String makes them both strings
        assertEquals(jsonActual.toString(), testCustomer1.toJson().toString());


    }
}