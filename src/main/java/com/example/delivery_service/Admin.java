package com.example.delivery_service;

import javafx.scene.control.Button;

public class Admin extends User {
    private String name;

    public Admin(String name, String login, String password) {
        super(login, password);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

