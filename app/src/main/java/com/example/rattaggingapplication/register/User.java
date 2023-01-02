package com.example.rattaggingapplication.register;

public class User {
    private int id;
    private  String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    private int feedBack;
    private String username;
    private String password;
    private int accessType;

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }


    public int getFeedBack() {
        return feedBack;
    }
    public void setFeedBack(int feedBack) {
        this.feedBack = feedBack;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAccessType() {
        return accessType;
    }
    public void setAccessType(int accessType) {
        this.accessType = accessType;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

