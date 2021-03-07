// structure from CPSC 210 JSON serialization DEMO

package persistence;

import model.Customer;
import model.CustomerList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    private Customer testCustomer1;
    private Customer testCustomer2;

    @Test
    void testWriterNoFileFound() {
        try {
            JsonWriter writer = new JsonWriter("./data/\notfound.json");
            writer.startWriting();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCustomerList() {
        try {
            CustomerList cl = new CustomerList("test");
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.startWriting();
            writer.write(cl);
            writer.closeWriting();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            cl = reader.read();
            assertEquals("test", cl.getListName());
            assertEquals(0, cl.getListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNotEmptyCustomerList() {
        testCustomer1 = new Customer("John", "Doe",
                "johndoe@gmail.com", "(604)333-3333", 1);
        testCustomer2 = new Customer("a","b","c","d",2);

        try {
            CustomerList cl = new CustomerList("test");
            cl.addCustomerToList(testCustomer1);
            cl.addCustomerToList(testCustomer2);
            JsonWriter writer = new JsonWriter("./data/testWriterNotEmpty.json");
            writer.startWriting();
            writer.write(cl);
            writer.closeWriting();

            JsonReader reader = new JsonReader("./data/testWriterNotEmpty.json");
            cl = reader.read();
            assertEquals("test", cl.getListName());
            assertEquals(2, cl.getListSize());
            ArrayList<Customer> testList = cl.getCustomerSoFar();
            checkCustomerListArray("John", "Doe", "johndoe@gmail.com", "(604)333-3333",
                    1, testList.get(0));
            checkCustomerListArray("a", "b", "c", "d",
                    2, testList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }


    }
}