package com.integrator.service.repository;

import com.integrator.service.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity,Integer>{

    @Query(value = "select sum(transactiondetails.transferred_Amt)" +
            " from transactiondetails " +
            "where transactiondetails.sender_Acct_No = ?1", nativeQuery = true)
    double getTransferredAmtBySender(Integer senderAcctNo);

    @Query(value = "select sum(transactiondetails.transferred_Amt)" +
            " from transactiondetails " +
            "where transactiondetails.receiver_Acct_No = ?1", nativeQuery = true)
    double getTransferredAmtByReceiver(Integer receiverAcctNo);

    @Query(value = "select sum(transactiondetails.transferred_Amt)" +
            " from transactiondetails " +
            "where transactiondetails.receiver_Acct_No=?1 and transactiondetails.sender_Acct_No=?1", nativeQuery = true)
    double getTransferredAmtByDeposit(Integer accountNo);

    @Query(value = "SELECT * FROM user_account WHERE account_No=?1", nativeQuery = true)
    UserAccountEntity getAccountBalanceByAccountNo(Integer account);

    List<UserAccountEntity> findByUserId(String userId);

//    UserAccountEntity findByAccountNo(Integer accountNo);
}

