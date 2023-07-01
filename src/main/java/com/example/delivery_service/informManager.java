package com.example.delivery_service;
import javafx.scene.control.Button;

public class informManager {
    private String name_manager;
    private String login_manager;
    private String center_delivery_manager;
    private Button button;

    public informManager(String name_manager, String login_manager, String center_delivery_manager, Button button) {
        this.name_manager = name_manager;
        this.login_manager = login_manager;
        this.center_delivery_manager = center_delivery_manager;
        this.button = button;
    }

    public String getName_manager() {
        return name_manager;
    }

    public void setName_manager(String name_manager) {
        this.name_manager = name_manager;
    }


    public String getLogin_manager() {
        return login_manager;
    }

    public void setLogin_manager(String login_manager) {
        this.login_manager = login_manager;
    }

    public String getCenter_delivery_manager() {
        return center_delivery_manager;
    }

    public void setCenter_delivery_manager(String center_delivery_manager) {
        this.center_delivery_manager = center_delivery_manager;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
