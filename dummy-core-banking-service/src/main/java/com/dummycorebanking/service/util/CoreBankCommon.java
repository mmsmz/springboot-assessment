package com.dummycorebanking.service.util;

public interface CoreBankCommon {

    String SUCCESS = "Success";

    String WRONGACCOUNT = "Account Number is Entered Wrong!!";

    String GET_ACCT_BALANCE_BY_ACCTNO = "/getAccountBalanceByAccountNo/";

    String GET_TOTALACCT_BALANCE_BY_USERID = "/getTotalAcctBalanceByUserId/";

    String MAKE_FUND_TRANSFER_TO_OWN_ACCT = "/makeFundTransferToOwnAccount/";

    String MAKE_FUND_TRANSFER_TO_OTHER_ACCT = "/makeFundTransferToOtherAccount/";

    String ACCOUNT_DOESNT_EXISTS = "You don't have a account!!!";

    String TRANSFERRED_SUCCESSFULLY = "Transferred Amount Successfully";

    String INSUFFICIENT_ACCT_BALANCE = "You don't have sufficient amount in the account";





}
