package com.integrator.service.service;


import com.integrator.service.dto.UserAccountDto;
import org.springframework.stereotype.Service;

@Service
public interface IntegratorService {
    String getAccountBalanceByAccountNo(Integer accountNo);

    UserAccountDto getTotalAcctBalanceByUserId(String userId);

    String makeFundTransferOwnAccount(Integer receiverAcctNo, double depositedAmount);

    String makeFundTransferToOtherAccount(Integer senderAcctNo, Integer receiverAcctNo, double depositedAmount);

    void saveAPIForAudit(String apiName, String paramWithValue);

}
