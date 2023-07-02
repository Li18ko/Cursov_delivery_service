package com.example.delivery_service;

public class Delivery_center {
    private int id;
    private String name;
    private String address;

    public Delivery_center(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Delivery_center(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
