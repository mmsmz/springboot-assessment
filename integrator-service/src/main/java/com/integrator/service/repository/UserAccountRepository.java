package com.integrator.service.repository;

import com.integrator.service.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity,Integer>{

    @Query(value = "select sum(transactiondetails.transferred_amt)" +
            " from transactiondetails " +
            "where transactiondetails.sender_acc = ?1", nativeQuery = true)
    double getTransferredAmtBySender(Integer senderAcctNo);

    @Query(value = "select sum(transactiondetails.transferred_amt)" +
            " from transactiondetails " +
            "where transactiondetails.receiver_acc = ?1", nativeQuery = true)
    double getTransferredAmtByReceiver(Integer receiverAcctNo);

    @Query(value = "select sum(transactiondetails.transferred_amt)" +
            " from transactiondetails " +
            "where transactiondetails.receiver_acc=?1 and transactiondetails.sender_acc=?1", nativeQuery = true)
    double getTransferredAmtByDeposit(Integer accountNo);

    @Query(value = "SELECT * FROM user_account WHERE accountno=?1", nativeQuery = true)
    UserAccountEntity getAccountBalanceByAccountNo(Integer account);

    List<UserAccountEntity> findByUserId(String userId);

//    UserAccountEntity findByAccountNo(Integer accountNo);
}

