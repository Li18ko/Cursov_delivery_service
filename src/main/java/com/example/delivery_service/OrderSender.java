package com.example.delivery_service;

public class OrderSender {
    private String id;
    private String data;
    private String name;
    private String number;
    private String status;

    public OrderSender(String id, String data, String name, String number, String status) {
        this.id = id;
        this.data = data;
        this.name = name;
        this.number = number;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
