package com.example.delivery_service;

import javafx.scene.control.Button;

public class DataCourier {

    private String parcle_id;
    private String name_recipient;
    private String number_recipient;
    private String address;
    private Button button;

    public DataCourier(String parcle_id, String name_recipient, String number_recipient, String address, Button button) {
        this.parcle_id = parcle_id;
        this.name_recipient = name_recipient;
        this.number_recipient = number_recipient;
        this.address = address;
        this.button = button;
    }

    public String getParcle_id() {
        return parcle_id;
    }

    public void setParcle_id(String parcle_id) {
        this.parcle_id = parcle_id;
    }

    public String getName_recipient() {
        return name_recipient;
    }

    public void setName_recipient(String name_recipient) {
        this.name_recipient = name_recipient;
    }

    public String getNumber_recipient() {
        return number_recipient;
    }

    public void setNumber_recipient(String number_recipient) {
        this.number_recipient = number_recipient;
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
}
