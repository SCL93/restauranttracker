package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a Customer List containing customer objects
public class CustomerList implements Writable {
    private String name;
    private ArrayList<Customer> customerSoFar;

    // EFFECTS: Constructs an empty customer list with a name
    public CustomerList(String name) {
        this.name = name;
        customerSoFar = new ArrayList<>();
    }

    public void addCustomerToList(Customer customer) {
        customerSoFar.add(customer);
    }

    public String getListName() {
        return name;
    }

    public int getListSize() {
        return customerSoFar.size();
    }

    public ArrayList<Customer> getCustomerSoFar() {
        return customerSoFar;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("customers", customerSoFarJson());
        return json;
    }

    // EFFECTS: returns customers in the list as JSON array
    private JSONArray customerSoFarJson() {
        JSONArray jsonArray = new JSONArray();
        for (Customer c : customerSoFar) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }


}
