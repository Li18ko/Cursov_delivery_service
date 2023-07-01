package com.example.delivery_service;

import java.awt.*;
import javafx.scene.control.Button;

public class DataManager {

    private String parcle_id;
    private String weight;
    private String typeDelivery;
    private String data;
    private String center_delivery;
    private Button button;

    public DataManager(String parcle_id, String weight, String typeDelivery, String data, String center_delivery) {
        this.parcle_id = parcle_id;
        this.weight = weight;
        this.typeDelivery = typeDelivery;
        this.data = data;
        this.center_delivery = center_delivery;
        this.button = new Button("Подтвердить");
    }

    public String getParcle_id() {
        return parcle_id;
    }

    public void setParcle_id(String parcle_id) {
        this.parcle_id = parcle_id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
