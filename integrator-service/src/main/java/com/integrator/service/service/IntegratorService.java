package com.integrator.service.service;


import com.integrator.service.dto.TransactionDetailDto;
import com.integrator.service.dto.UserAccountDto;
import org.springframework.stereotype.Service;

@Service
public interface IntegratorService {
    String getAccountBalanceByAccountNo(Integer accountNo);

    UserAccountDto getTotalAcctBalanceByUserId(String userId);

    TransactionDetailDto makeFundTransferOwnAccount(Integer receiverAcctNo, double depositedAmount);

    TransactionDetailDto makeFundTransferToOtherAccount(Integer senderAcctNo, Integer receiverAcctNo, double depositedAmount);

}
