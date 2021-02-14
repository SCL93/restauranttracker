package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AlertTest {
    private Customer testCustomer1;
    private Customer testCustomer2;
    private Customer testCustomer3;
    private Customer testCustomer4;

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
    }

    @Test
        // test othersInfected using upperbound int=4, 2 OR  options, should produce 2 customers
    void testOthersInfected1() {
        ArrayList<Customer> testList = new ArrayList<>();
        testList.add(testCustomer1);
        testList.add(testCustomer2);
        testList.add(testCustomer3);
        testList.add(testCustomer4);

        ArrayList<Customer> testingList = Alert.othersInfected(4, testList);

        Object[] newList = {testingList.get(0), testingList.get(1)};
        Object[] expected = {testCustomer3, testCustomer4};
        assertArrayEquals(newList, expected);

    }

    @Test
        // test othersInfected with ALL OR options, should produce 2 customers
    void testOthersInfected2() {
        ArrayList<Customer> testList = new ArrayList<>();
        testList.add(testCustomer1);
        testList.add(testCustomer2);
        testList.add(testCustomer3);
        testList.add(testCustomer4);

        ArrayList<Customer> testingList = Alert.othersInfected(3, testList);

        Object[] newList = {testingList.get(0), testingList.get(1), testingList.get(2)};
        Object[] expected = {testCustomer2, testCustomer3, testCustomer4};
        assertArrayEquals(newList, expected);

    }

    @Test
        // test othersInfected with lower bound int=1, 2 OR options, should produce 2 customers
    void testOthersInfected3() {
        ArrayList<Customer> testList = new ArrayList<>();
        testList.add(testCustomer1);
        testList.add(testCustomer2);
        testList.add(testCustomer3);
        testList.add(testCustomer4);

        ArrayList<Customer> testingList = Alert.othersInfected(1, testList);

        Object[] newList = {testingList.get(0), testingList.get(1)};
        Object[] expected = {testCustomer1, testCustomer2};
        assertArrayEquals(newList, expected);
    }
}