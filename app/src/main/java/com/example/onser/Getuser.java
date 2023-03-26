package com.example.onser;

public class Getuser {
    String name,email,phonenumber,address;
    public Getuser(){}



    public Getuser(String name, String email, String phonenumber, String address) {

        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address=address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
