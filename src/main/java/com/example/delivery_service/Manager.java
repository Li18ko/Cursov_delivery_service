package com.example.delivery_service;

public class Manager {
    private String delivery_center;
    private String name;
    private String login;
    private String hashedPassword;

    public Manager(String delivery_center, String name, String login, String hashedPassword) {
        this.delivery_center = delivery_center;
        this.name = name;
        this.login = login;
        this.hashedPassword = hashedPassword;
    }

    public String getDelivery_center() {
        return delivery_center;
    }

    public void setDelivery_center(String delivery_center_id) {
        this.delivery_center = delivery_center;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return hashedPassword;
    }

    public void setPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
