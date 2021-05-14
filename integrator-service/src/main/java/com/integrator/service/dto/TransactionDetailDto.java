package com.integrator.service.dto;

import java.time.Instant;

public class TransactionDetailDto {

    private Integer senderAccountNo;

    private Integer receiverAccountNo;

    private Double transferredAmount;

    private Instant dateTime;


    public Integer getSenderAccountNo() {
        return senderAccountNo;
    }

    public void setSenderAccountNo(Integer senderAccountNo) {
        this.senderAccountNo = senderAccountNo;
    }

    public Integer getReceiverAccountNo() {
        return receiverAccountNo;
    }

    public void setReceiverAccountNo(Integer receiverAccountNo) {
        this.receiverAccountNo = receiverAccountNo;
    }

    public Double getTransferredAmount() {
        return transferredAmount;
    }

    public void setTransferredAmount(Double transferredAmount) {
        this.transferredAmount = transferredAmount;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "TransactionDetailDto{" +
                "senderAccountNo=" + senderAccountNo +
                ", receiverAccountNo=" + receiverAccountNo +
                ", transferredAmount=" + transferredAmount +
                ", dateTime=" + dateTime +
                '}';
    }
}
