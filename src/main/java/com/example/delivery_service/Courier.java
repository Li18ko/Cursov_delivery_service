package com.example.delivery_service;

import javafx.scene.control.Button;

public class Courier {
    private Button button;
    private String address;
    private String number_recipient;
    private String name_recipient;
    private String parcles_id;
    private String name;
    private String number;
    private String login;
    private String center_delivery;
    private String password;

    public Courier(String name, String number, String center_delivery, String login, String hashedPassword) {
        this.name = name;
        this.number = number;
        this.login = login;
        this.password = hashedPassword;
        this.center_delivery = center_delivery;
    }

    public Courier(String parcels_id, String name_recipient, String number_recipient, String address, Button button) {
        this.parcles_id = parcels_id;
        this.name_recipient = name_recipient;
        this.number_recipient = number_recipient;
        this.address = address;
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber_recipient() {
        return number_recipient;
    }

    public void setNumber_recipient(String number_recipient) {
        this.number_recipient = number_recipient;
    }

    public String getName_recipient() {
        return name_recipient;
    }

    public void setName_recipient(String name_recipient) {
        this.name_recipient = name_recipient;
    }

    public String getParcles_id() {
        return parcles_id;
    }

    public void setParcles_id(String parcles_id) {
        this.parcles_id = parcles_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCenter_delivery() {
        return center_delivery;
    }

    public void setCenter_delivery(String center_delivery) {
        this.center_delivery = center_delivery;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
