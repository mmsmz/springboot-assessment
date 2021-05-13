package com.integrator.service.service;


import com.integrator.service.dto.TransactionDetailDto;
import org.springframework.stereotype.Service;

@Service
public interface IntegratorService {
    double getAccountBalanceByAccountNo(Integer accountNo);

    TransactionDetailDto makeFundTransferOwnAccount(Integer receiverAcctNo, double depositedAmount);

    TransactionDetailDto makeFundTransferToOtherAccount(Integer senderAcctNo, Integer receiverAcctNo, double depositedAmount);

}
