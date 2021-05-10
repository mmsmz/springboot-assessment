package com.integrator.service.repository;

import com.integrator.service.entity.TransactionDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetailEntity, Integer> {


    TransactionDetailEntity findBySenderAccountNo(Integer senderAccountNo);
    TransactionDetailEntity findByReceiverAccountNo(Integer receiverAccountNo);

}

