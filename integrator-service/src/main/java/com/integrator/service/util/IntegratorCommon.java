package com.integrator.service.util;

import com.integrator.service.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.DocFlavor;

public interface IntegratorCommon {

    String SUCCESS = "Success";

    String WRONGACCOUNT = "Account Number is Entered Wrong!!";

    String GET_ACCT_BALANCE_BY_ACCTNO = "/getAccountBalanceByAccountNo/{accountNo}";

    String GET_TOTALACCT_BALANCE_BY_USERID = "/getTotalAcctBalanceByUserId/{userId}";

    String MAKE_FUND_TRANSFER_TO_OWN_ACCT = "/makeFundTransferToOwnAccount";

    String MAKE_FUND_TRANSFER_TO_OTHER_ACCT = "/makeFundTransferToOtherAccount";

    String ACCOUNT_DOESNT_EXISTS = "You don't have a account!!!";

    String TRANSFERRED_SUCCESSFULLY = "Transferred Amount Successfully";

    String INSUFFICIENT_ACCT_BALANCE = "You don't have sufficient amount in the account";



}
