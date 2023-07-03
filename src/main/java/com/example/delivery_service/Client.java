package com.example.delivery_service;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class Client {
    private Button button;
    private ComboBox<String> address_cd;
    private String address_client;
    private String id;
    private String name;
    private String number;
    private String address;
    private String login;
    private String password;

    public Client(String name, String number, String address, String login, String password) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.login = login;
        this.password = password;
    }

    public Client(String id, String address_client, ComboBox<String> address_cd, Button button) {
        this.id= id;
        this.address_client = address_client;
        this.address_cd = address_cd;
        this.button = button;
    }

    public Client(String loginText, String passwordText) {
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

    public String getAddress_client() {
        return address_client;
    }

    public void setAddress_client(String address_client) {
        this.address_client = address_client;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
