package com.example.delivery_service;

import javafx.scene.control.Button;

public class informAdmin{
    private String center_delivery_manager;
    private String login_manager;
    private String name_manager;
    private String name_courier;
    private String number_courier;
    private String login_courier;
    private String center_delivery_courier;
    private Button button;

    public informAdmin(String name_courier, String number_courier, String login_courier, String center_delivery_courier, Button button) {
        this.name_courier = name_courier;
        this.number_courier = number_courier;
        this.login_courier = login_courier;
        this.center_delivery_courier = center_delivery_courier;
        this.button = button;
    }

    public informAdmin(String name_manager, String login_manager, String center_delivery_manager, Button button) {
        this.name_manager = name_manager;
        this.login_manager = login_manager;
        this.center_delivery_manager = center_delivery_manager;
        this.button = button;
    }


    public String getCenter_delivery_manager() {
        return center_delivery_manager;
    }

    public void setCenter_delivery_manager(String center_delivery_manager) {
        this.center_delivery_manager = center_delivery_manager;
    }

    public String getLogin_manager() {
        return login_manager;
    }

    public void setLogin_manager(String login_manager) {
        this.login_manager = login_manager;
    }

    public String getName_manager() {
        return name_manager;
    }

    public void setName_manager(String name_manager) {
        this.name_manager = name_manager;
    }

    public String getName_courier() {
        return name_courier;
    }

    public void setName_courier(String name_courier) {
        this.name_courier = name_courier;
    }

    public String getNumber_courier() {
        return number_courier;
    }

    public void setNumber_courier(String number_courier) {
        this.number_courier = number_courier;
    }

    public String getLogin_courier() {
        return login_courier;
    }

    public void setLogin_courier(String login_courier) {
        this.login_courier = login_courier;
    }

    public String getCenter_delivery_courier() {
        return center_delivery_courier;
    }

    public void setCenter_delivery_courier(String center_delivery_courier) {
        this.center_delivery_courier = center_delivery_courier;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
