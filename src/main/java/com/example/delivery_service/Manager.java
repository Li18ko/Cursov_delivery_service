package com.example.delivery_service;

import javafx.scene.control.Button;

public class Manager {
    private Button button;
    private String weight;
    private String parcle_id;
    private String typeDelivery;
    private String data;
    private String center_delivery;
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

    public Manager(String parcle_id, String weight, String typeDelivery, String data, String center_delivery, Button button) {
        this.parcle_id = parcle_id;
        this.weight = weight;
        this.typeDelivery = typeDelivery;
        this.data = data;
        this.center_delivery = center_delivery;
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

    public String getParcle_id() {
        return parcle_id;
    }

    public void setParcle_id(String parcle_id) {
        this.parcle_id = parcle_id;
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

    public String getCenter_delivery() {
        return center_delivery;
    }

    public void setCenter_delivery(String center_delivery) {
        this.center_delivery = center_delivery;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
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
