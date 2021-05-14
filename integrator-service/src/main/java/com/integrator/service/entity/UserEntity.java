package com.integrator.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "user_Id")
    private String userId;

    @Column(name = "user_Name")
    private Integer userName;

    @Column(name = "email")
    private double email;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
