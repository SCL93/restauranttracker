package ui.tabs;

import model.CustomerList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.RestaurantApp;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public abstract class Tab extends JPanel {
    private RestaurantApp controller;

    //REQUIRES: RestaurantApp controller that holds this tab
    public Tab(RestaurantApp controller) {
        this.controller = controller;

    }

    //EFFECTS: returns the RestaurantApp controller for this tab
    public RestaurantApp getController() {
        return controller;
    }

}
