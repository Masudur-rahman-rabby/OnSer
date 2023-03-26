package com.example.onser;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class workers implements Serializable {

    @Exclude
    String key;
    String name,phone,service;

    public workers(){}

    public workers(String name, String phone, String service) {
        this.name = name;
        this.phone = phone;
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
