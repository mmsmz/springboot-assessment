package com.integrator.service.repository;

import com.integrator.service.entity.TransactionDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetailEntity, Integer> {

    TransactionDetailEntity findBySenderAccountNo(Integer senderAccountNo);

    TransactionDetailEntity findByReceiverAccountNo(Integer receiverAccountNo);

    /*SELECT count(transaction_details.transferred_Amt) as tot,
    IF(count(transaction_details.transferred_Amt)>0, (select sum(transaction_details.transferred_Amt) from transaction_details where transaction_details.sender_Acct_No=456789456), 0) FROM transaction_details where transaction_details.sender_Acct_No=456789456;
*/


//    @Query(value = "select sum(transaction_details.transferred_Amt)" +
//            " from transaction_details " +
//            "where transaction_details.sender_Acct_No = ?1", nativeQuery = true)
//    double getTransferredAmtBySender(Integer senderAcctNo);

    @Query(value = "SELECT IF(count(transaction_details.transferred_Amt)>0, " +
            "(select sum(transaction_details.transferred_Amt) " +
            "FROM transaction_details where transaction_details.sender_Acct_No=?1), 0) \n" +
            "FROM transaction_details where transaction_details.sender_Acct_No=?1", nativeQuery = true)
    double getTransferredAmtBySender(Integer senderAcctNo);


    @Query(value = "SELECT IF(count(transaction_details.transferred_Amt)>0, " +
            "(select sum(transaction_details.transferred_Amt) " +
            "FROM transaction_details where transaction_details.receiver_Acct_No=?1), 0) " +
            "FROM transaction_details where transaction_details.receiver_Acct_No=?1", nativeQuery = true)
    double getTransferredAmtByReceiver(Integer receiverAcctNo);

//    @Query(value = "select sum(transaction_details.transferred_Amt)" +
//            " from transaction_details " +
//            "where transaction_details.receiver_Acct_No=?1 and transaction_details.sender_Acct_No=?1", nativeQuery = true)
//
    @Query(value = "SELECT IF(count(transaction_details.transferred_Amt)>0, " +
            "(select sum(transaction_details.transferred_Amt) " +
            "FROM transaction_details where transaction_details.receiver_Acct_No=?1 and transaction_details.sender_Acct_No=?1), 0) " +
            "FROM transaction_details where transaction_details.receiver_Acct_No=?1 and transaction_details.sender_Acct_No=?1", nativeQuery = true)
    double getTransferredAmtByDeposit(Integer accountNo);

}

