package com.example.delivery_service;

import java.sql.Timestamp;

public class Parcle {
    private String typeDelivery;
    private String weight;
    private String data;
    private String recipientName;
    private String recipientNumber;

    public Parcle(String typeDelivery, String weight, String data, String recipientName, String recipientNumber) {
        this.typeDelivery = typeDelivery;
        this.weight = weight;
        this.data = data;
        this.recipientName = recipientName;
        this.recipientNumber = recipientNumber;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public void setRecipientNumber(String recipientNumber) {
        this.recipientNumber = recipientNumber;
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
}
