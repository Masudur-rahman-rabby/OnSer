package com.example.onser;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class BookingClass implements Serializable {

    @Exclude
    String key;
    String services,address,date,time,cost;

    public BookingClass(){}

    public BookingClass(String services, String address, String date, String time,String cost) {
        this.services = services;
        this.address = address;
        this.date = date;
        this.time = time;
        this.cost= cost;
    }


    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
