package com.example.app_android_weather.Model;

import android.text.Editable;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phonenumber;

    public User() {
    }
    public User(int id, String username, String password, String name, String email, String phonenumber){
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
    }
    public User(String username, String password, String name, String email, String phonenumber){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}
