package com.example.delivery_service;

import javafx.scene.control.Button;

public class OrderRecipient {
    private String rec_id;
    private String rec_data;
    private String name;
    private String number;
    private Button button;

    public OrderRecipient(String id, String data, String name, String number, Button button) {
        this.rec_id = id;
        this.rec_data = data;
        this.name = name;
        this.number = number;
        this.button = button;
    }

    public String getRec_id() {
        return rec_id;
    }

    public void setRec_id(String rec_id) {
        this.rec_id = rec_id;
    }

    public String getRec_data() {
        return rec_data;
    }

    public void setRec_data(String rec_data) {
        this.rec_data = rec_data;
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

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
