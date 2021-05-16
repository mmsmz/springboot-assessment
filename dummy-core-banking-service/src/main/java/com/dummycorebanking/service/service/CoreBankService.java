package com.dummycorebanking.service.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URISyntaxException;

@Service
public interface CoreBankService {
    String getAccountBalanceByAccountNo(Integer accountNo);

    double getTotalAcctBalanceByUserId(String userId);

    String makeFundTransferOwnAccount(Integer receiverAcctNo, double depositedAmount) throws URISyntaxException;

    String makeFundTransferToOtherAccount(Integer senderAcctNo, Integer receiverAcctNo, double depositedAmount);

}
