package com.example.delivery_service;

import javafx.scene.control.Button;

public class Manager extends User{
    private String delivery_center;
    private String name;
    public Manager(String delivery_center, String name, String login, String password) {
        super(login, password);
        this.delivery_center = delivery_center;
        this.name = name;
    }

    public String getDelivery_center() {
        return delivery_center;
    }

    public void setDelivery_center(String delivery_center) {
        this.delivery_center = delivery_center;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
