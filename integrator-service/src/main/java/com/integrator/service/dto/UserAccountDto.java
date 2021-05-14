package com.integrator.service.dto;

import org.omg.CORBA.INTERNAL;

import java.util.List;

public class UserAccountDto {

    private String userId;

//    private Integer accountNo;

    private double balanceAmount;

    private List<Integer> listOfAccountNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

//    public Integer getAccountNo() {
//        return accountNo;
//    }
//
//    public void setAccountNo(Integer accountNo) {
//        this.accountNo = accountNo;
//    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public List<Integer> getListOfAccountNo() {
        return listOfAccountNo;
    }

    public void setListOfAccountNo(List<Integer> listOfAccountNo) {
        this.listOfAccountNo = listOfAccountNo;
    }
}
