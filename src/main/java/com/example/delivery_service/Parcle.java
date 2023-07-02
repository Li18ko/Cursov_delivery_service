package com.example.delivery_service;

import javafx.scene.control.Button;

import java.sql.Timestamp;

public class Parcle {
    private Button button;
    private String numberSenders;
    private String nameSenders;
    private String typeDelivery;
    private String weight;
    private String data;
    private int id;
    private String status;
    private String recipientName;
    private String recipientNumber;

    public Parcle(String typeDelivery, String weight, String data, String recipientName, String recipientNumber) {
        this.typeDelivery = typeDelivery;
        this.weight = weight;
        this.data = data;
        this.recipientName = recipientName;
        this.recipientNumber = recipientNumber;
    }

    public Parcle(int id, String data, String recipientName, String recipientNumber, String status) {
        this.id = id;
        this.data = data;
        this.recipientName = recipientName;
        this.recipientNumber = recipientNumber;
        this.status = status;
    }

    public Parcle(String id, String data, String nameSenders, String numberSenders, Button button) {
        this.id = Integer.parseInt(id);
        this.data = data;
        this.nameSenders = nameSenders;
        this.numberSenders = numberSenders;
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getNumberSenders() {
        return numberSenders;
    }

    public void setNumberSenders(String numberSenders) {
        this.numberSenders = numberSenders;
    }

    public String getNameSenders() {
        return nameSenders;
    }

    public void setNameSenders(String nameSenders) {
        this.nameSenders = nameSenders;
    }

    public String getId() {
        return String.valueOf(id);
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
