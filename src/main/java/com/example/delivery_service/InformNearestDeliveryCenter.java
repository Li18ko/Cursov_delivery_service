package com.example.delivery_service;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;

public class InformNearestDeliveryCenter {
    private String id;
    private String address;
    private Button button;
    private ComboBox<String> address_cd;
    public InformNearestDeliveryCenter(String id, String address, ComboBox<String> address_cd, Button button) {
        this.id= id;
        this.address = address;
        this.address_cd = address_cd;
        this.button = button;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public ComboBox<String> getAddress_cd() {
        return address_cd;
    }

    public void setAddress_cd(ComboBox<String> address_cd) {
        this.address_cd = address_cd;
    }
}
