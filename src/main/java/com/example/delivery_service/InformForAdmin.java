package com.example.delivery_service;

import javafx.scene.control.Button;

public class InformForAdmin {
    private String name;
    private String number;
    private String login;
    private String center_delivery;
    private Button button;

    public InformForAdmin(String name, String number, String login, String center_delivery, Button button) {
        this.name = name;
        this.number = number;
        this.login = login;
        this.center_delivery = center_delivery;
        this.button = button;
    }

    public InformForAdmin(String name, String login, String center_delivery, Button button) {
        this.name = name;
        this.login = login;
        this.center_delivery = center_delivery;
        this.button = button;
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

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
