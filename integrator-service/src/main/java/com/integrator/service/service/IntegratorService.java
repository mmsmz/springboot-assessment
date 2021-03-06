package com.integrator.service.service;


import org.springframework.stereotype.Service;

@Service
public interface IntegratorService {
    String getAccountBalanceByAccountNo(Integer accountNo);

    double getTotalAcctBalanceByUserId(String userId);

    String makeFundTransferOwnAccount(Integer receiverAcctNo, double depositedAmount);

    String makeFundTransferToOtherAccount(Integer senderAcctNo, Integer receiverAcctNo, double depositedAmount);

    void saveAPIForAudit(String apiName, String paramWithValue);

}
