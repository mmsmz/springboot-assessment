package com.dummycorebanking.service.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public interface CoreBankService {
    String getAccountBalanceByAccountNo(Integer accountNo);

    double getTotalAcctBalanceByUserId(String userId);

    String makeFundTransferOwnAccount(Integer receiverAcctNo, double depositedAmount);

    String makeFundTransferToOtherAccount(Integer senderAcctNo, Integer receiverAcctNo, double depositedAmount);

}
