package com.integrator.service.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "transaction_details")
public class TransactionDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "td_Id")
    private Integer transactionId;

    @Column(name = "sender_Acct_No")
    private Integer senderAccountNo;

    @Column(name = "receiver_Acct_No")
    private Integer receiverAccountNo;

    @Column(name = "transferred_Amt")
    private Double transferredAmount;

    @Column(name = "date_Time")
    private Instant dateTime;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

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
}
