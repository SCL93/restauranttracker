package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerListTest {
    private CustomerList customerListTest1;
    private Customer testCustomer1;
    private ArrayList<Customer> testList1;

    @BeforeEach
    void runBefore(){
        testCustomer1 = new Customer("John", "Doe",
                "johndoe@gmail.com", "(604)333-3333", 1);
        customerListTest1 = new CustomerList("test");
        testList1 = new ArrayList<>();
          }

    @Test
    void testConstructor(){
        assertEquals("test",customerListTest1.getListName());
    }

    @Test
    void testAddCustomer(){
        customerListTest1.addCustomerToList(testCustomer1);
        assertEquals(1, customerListTest1.getListSize());
    }

    @Test
    void getCustomerSoFarEmpty(){
        ArrayList<Customer> tester = customerListTest1.getCustomerSoFar();
        int testerSize = tester.size();
        assertEquals(0,testerSize);
    }

    @Test
    void getCustomerSoFarAdd() {
        ArrayList<Customer> tester = customerListTest1.getCustomerSoFar();
        tester.add(testCustomer1);
        int testerSize = tester.size();
        assertEquals(1, testerSize);
    }
//
//    @Test
//    void testToJson(){
//        testList1.add(testCustomer1);
//        JSONObject testActual = new JSONObject();
//        testActual.put("name", "test");
//        testActual.put("customers","" )
//
//    }

}
