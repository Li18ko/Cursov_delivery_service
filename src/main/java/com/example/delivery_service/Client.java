package com.example.delivery_service;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class Client extends User {
    private Button button;
    private ComboBox<String> address_cd;
    private String id;
    private String name;
    private String number;
    private String address;

    public Client(String name, String number, String address, String login, String password) {
        super(login, password);
        this.name = name;
        this.number = number;
        this.address = address;
    }

    public Client(String id, String address, ComboBox<String> address_cd, Button button) {
        super();
        this.id= id;
        this.address = address;
        this.address_cd = address_cd;
        this.button = button;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
