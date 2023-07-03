package com.example.delivery_service;

import javafx.scene.control.Button;

public class Manager extends User{
    private Button button;
    private String weight;
    private String parcels_id;
    private String typeDelivery;
    private String data;
    private String delivery_center;
    private String name;
    public Manager(String delivery_center, String name, String login, String password) {
        super(login, password);
        this.delivery_center = delivery_center;
        this.name = name;
    }

    public Manager(String parcels_id, String weight, String typeDelivery, String data, String delivery_center, Button button) {
        this.parcels_id = parcels_id;
        this.weight = weight;
        this.typeDelivery = typeDelivery;
        this.data = data;
        this.delivery_center = delivery_center;
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getParcels_id() {
        return parcels_id;
    }

    public void setParcels_id(String parcels_id) {
        this.parcels_id = parcels_id;
    }

    public String getTypeDelivery() {
        return typeDelivery;
    }

    public void setTypeDelivery(String typeDelivery) {
        this.typeDelivery = typeDelivery;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
