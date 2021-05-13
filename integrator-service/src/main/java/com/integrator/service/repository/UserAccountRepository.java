package com.integrator.service.repository;

import com.integrator.service.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity,Integer>{

    @Query(value = "select sum(transactiondetails.transferred_amt)" +
            " from transactiondetails " +
            "where transactiondetails.sender_acc = ?1", nativeQuery = true)
    double getTransferredAmtBySender(Integer sender_acc);

    @Query(value = "select sum(transactiondetails.transferred_amt)" +
            " from transactiondetails " +
            "where transactiondetails.receiver_acc = ?1", nativeQuery = true)
    double getTransferredAmtByReceiver(Integer receiver_acc);

    @Query(value = "select sum(transactiondetails.transferred_amt)" +
            " from transactiondetails " +
            "where transactiondetails.receiver_acc=?1 and transactiondetails.sender_acc=?1", nativeQuery = true)
    double getTransferredAmtByDeposit(Integer accountNo);

    @Query(value = "SELECT * FROM user_account WHERE accountno=?1", nativeQuery = true)
    UserAccountEntity getAccountBalanceByAccountNo(Integer account);


//    UserAccountEntity findByAccountNo(Integer accountNo);
}

