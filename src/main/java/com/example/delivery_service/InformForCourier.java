package com.example.delivery_service;

import javafx.scene.control.Button;

public class InformForCourier {
    private Button button;
    private String address;
    private String number;
    private String parcels_id;
    private String name;

    public InformForCourier(String parcels_id, String name, String number, String address, Button button) {
        this.parcels_id = parcels_id;
        this.name = name;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getParcels_id() {
        return parcels_id;
    }

    public void setParcels_id(String parcels_id) {
        this.parcels_id = parcels_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
