package com.example.delivery_service;

public class Courier {
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
