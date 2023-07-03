package com.example.delivery_service;

import javafx.scene.control.Button;

import java.sql.Timestamp;

public class Parcle {
    private Button button;
    private String number;
    private String name;
    private String typeDelivery;
    private String weight;
    private String data;
    private int id;
    private String status;

    public Parcle(String typeDelivery, String weight, String data, String name, String number) {
        this.typeDelivery = typeDelivery;
        this.weight = weight;
        this.data = data;
        this.name = name;
        this.number = number;
    }

    public Parcle(int id, String data, String name, String number, String status) {
        this.id = id;
        this.data = data;
        this.name = name;
        this.number = number;
        this.status = status;
    }

    public Parcle(String id, String data, String name, String number, Button button) {
        this.id = Integer.parseInt(id);
        this.data = data;
        this.name = name;
        this.number = number;
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeDelivery() {
        return typeDelivery;
    }

    public void setTypeDelivery(String typeDelivery) {
        this.typeDelivery = typeDelivery;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
