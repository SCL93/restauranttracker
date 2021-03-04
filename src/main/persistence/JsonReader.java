package persistence;

import model.Customer;
import model.CustomerList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String sourceFile;

    // EFFECTS: constructs jsonReader to read from source file
    public JsonReader(String saveFile) {
        this.sourceFile = saveFile;
    }

    // EFFECTS: reads CustomerList from source file, and returns it
    public CustomerList read() throws IOException {
        String jsonData = readFile(sourceFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCustomerList(jsonObject);
    }

    // EFFECTS: read source file as string and returns the string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses customerList from saved JSON Object and returns it
    private CustomerList parseCustomerList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        CustomerList cl = new CustomerList(name);
        addCustomers(cl, jsonObject);
        return cl;
    }

    // MODIFIES: customerList cl (created in parseCustomerList)
    // EFFECTS: parse customers from JSON object and adds it to CustomerList array (non JSON object)
    private void addCustomers(CustomerList cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("customers");
        for (Object json : jsonArray) {
            JSONObject nextCustomers = (JSONObject) json;
            recreateAddCustomer(cl, nextCustomers);
        }
    }

    // MODIFIES: customerList cl (created in parseCustomerList)
    // EFFECTS: parse individual customers from JSON object, adds it to CustomerList Array
    private void recreateAddCustomer(CustomerList cl, JSONObject jsonObject) {
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        String email = jsonObject.getString("email");
        String phone = jsonObject.getString("phoneNumber");
        int checkInTime = jsonObject.getInt("checkInTime");
        Customer customer = new Customer(firstName, lastName, email, phone, checkInTime);
        cl.addCustomerToList(customer);
    }

}


