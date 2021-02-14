package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InfectedTest {
    private Customer testCustomer1;
    private Customer testCustomer2;
    private Customer testCustomer3;
    private Customer testCustomer4;
    ArrayList<Customer> testList = new ArrayList<>();

    @BeforeEach
    void runBefore() {
        testCustomer1 = new Customer("John", "Doe",
                "johndoe@gmail.com", "(604)333-3333", 1);
        testCustomer2 = new Customer("tom", "brady",
                "tombrady@gmail.com", "604304540", 2);
        testCustomer3 = new Customer("patrick", "maholmes",
                "pm@gmail.com", "840643210", 3);
        testCustomer4 = new Customer("markus", "naslund",
                "mk@gmail.com", "454034073", 4);

        testList.add(testCustomer1);
        testList.add(testCustomer2);
        testList.add(testCustomer3);
        testList.add(testCustomer4);

    }

    @Test
        // test randomCovidSelector to see if truly random
    void testRandomCovidSelect() {
        int timesDifferentIndexSelected = 0;
        int lastIndexNumber = 0;
        for (int i = 0; i < 10; i++) {
            int randomNumber = Infected.randomCovidSelect(testList);
            if(lastIndexNumber != randomNumber) {
                timesDifferentIndexSelected++;
                lastIndexNumber = randomNumber;
            }
        }
        assertTrue(timesDifferentIndexSelected != 0);
    }

    @Test
        // test covidSelectCustomer method
    void testCovidSelectCustomer() {
        Customer resultCustomer = Infected.covidSelectedCustomer(testList, 3);
        assertEquals(testCustomer4, resultCustomer);
    }

    @Test
        // test covidSelectName method
    void testCovidSelectName() {
        String resultName = Infected.covidSelectedName(testCustomer1);
        assertEquals("John", resultName);
    }

    @Test
        // test covidSelectTime method
    void testCovidSelectTime() {
        int resultTime = Infected.covidSelectedTime(testCustomer4);
        assertEquals(4, resultTime);
    }
}