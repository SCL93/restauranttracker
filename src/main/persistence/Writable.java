// structure from CPSC 210 JSON serialization DEMO

package persistence;


import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON Object
    JSONObject toJson();
}
