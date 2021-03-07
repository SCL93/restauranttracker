// structure from CPSC 210 JSON serialization DEMO

package persistence;

import model.CustomerList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private int indentFactor = 2;
    private PrintWriter writer;
    private String fileLocation;

    // EFFECTS: constructs writer to write to data file
    public JsonWriter(String fileName) {
        this.fileLocation = fileName;
    }

    // MODIFIES: this
    // EFFECTS: start writer, throws exception if destination file name/location cannot be found
    public void startWriting() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileLocation));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Customer object to file
    public void write(CustomerList cl) {
        JSONObject json = cl.toJson();
        saveToFile(json.toString(indentFactor));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void closeWriting() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}


