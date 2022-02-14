package com.example.rattaggingapplication.register;

public class Rat {
    private String name;
    private String datOfBrith;
    private String sex;
    private int userId;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRatSex() {
        return sex;
    }
    public void setRatSex(String sex) {
        this.sex = sex;
    }
    public String getDateOfBirth() {
        return datOfBrith;
    }
    public void setDateOfBirth(String datOfBrith) {
        this.datOfBrith = datOfBrith;
    }
    public int getuserId() {
        return userId;
    }
    public void setuserId(int userId) {
        this.userId = userId;
    }
}
