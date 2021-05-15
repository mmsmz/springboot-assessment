package com.dummycorebanking.service.dto;

public class UserDto {

    private Integer userName;

    private double email;

    public Integer getUserName() {
        return userName;
    }

    public void setUserName(Integer userName) {
        this.userName = userName;
    }

    public double getEmail() {
        return email;
    }

    public void setEmail(double email) {
        this.email = email;
    }
}
