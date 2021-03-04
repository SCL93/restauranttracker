package persistence;

import model.Customer;
import model.CustomerList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderFileNotFound() {
        try {
            JsonReader reader = new JsonReader("./data/notfound.json");
            CustomerList cl = reader.read();
            fail("IO Exception expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderEmptyCustomerList() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            CustomerList cl = reader.read();
            assertEquals("test", cl.getListName());
            assertEquals(0, cl.getListSize());
        } catch (IOException e) {
            fail("File could not be read");
        }
    }

    @Test
    void testReaderNotEmptyCustomerList() {
        JsonReader reader = new JsonReader("./data/testReaderNotEmpty.json");
        try {
            CustomerList cl = reader.read();
            assertEquals("test", cl.getListName());
            assertEquals(1, cl.getListSize());
            ArrayList<Customer> testList = cl.getCustomerSoFar();
            checkCustomerListArray("John", "Doe", "johndoe@gmail.com",
                    "(604)333-3333", 1, testList.get(0));


        } catch (IOException e) {
            fail("File could not be read");
        }

    }
}