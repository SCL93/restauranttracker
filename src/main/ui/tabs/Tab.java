package ui.tabs;

import ui.RestaurantApp;

import javax.swing.*;


public abstract class Tab extends JPanel {
    private final RestaurantApp controller;

    //REQUIRES: RestaurantApp controller that holds this tab
    public Tab(RestaurantApp controller) {
        this.controller = controller;

    }

    //EFFECTS: returns the RestaurantApp controller for this tab
    public RestaurantApp getController() {
        return controller;
    }

}
