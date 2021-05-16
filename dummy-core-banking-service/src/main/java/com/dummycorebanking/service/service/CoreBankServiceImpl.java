package com.dummycorebanking.service.service;

import com.dummycorebanking.service.dto.ResponseDto;
import com.dummycorebanking.service.util.CoreBankCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CoreBankServiceImpl implements CoreBankService {

    @Autowired
    private RestTemplate restTemplate;

    static final String BASEURL = "http://localhost:9901";

    @Override
    public String getAccountBalanceByAccountNo(Integer accountNo) {

        ResponseDto responseDto = restTemplate.getForEntity(BASEURL+CoreBankCommon.GET_ACCT_BALANCE_BY_ACCTNO + accountNo, ResponseDto.class).getBody();

        return responseDto.getData().toString();
    }

    /**
     * get total amount from all account numbers for a specific user
     * Param userId
     * return total Balance from number of accounts based on the userId
     * */
    @Override
    public double getTotalAcctBalanceByUserId(String userId) {

        ResponseDto responseDto = restTemplate.getForEntity(BASEURL+CoreBankCommon.GET_TOTALACCT_BALANCE_BY_USERID + userId, ResponseDto.class).getBody();
        return (double) responseDto.getData();
    }

    @Override
    public String makeFundTransferOwnAccount(Integer receiverAcctNo, double depositedAmount) {
        String api = "/makeFundTransferToOwnAccount?receiverAcctNo="+receiverAcctNo + "&depositedAmount=" + depositedAmount;
        ResponseDto responseDto = restTemplate.getForEntity(BASEURL+api, ResponseDto.class).getBody();
        return responseDto.getData().toString();
    }

    @Override
    public String makeFundTransferToOtherAccount(Integer senderAcctNo, Integer receiverAcctNo, double depositedAmount) {
      //  String api = "/makeFundTransferToOtherAccount?senderAcctNo="+senderAcctNo+"&receiverAcctNo="+receiverAcctNo + "&depositedAmount=" + depositedAmount;
        ResponseDto responseDto = restTemplate.getForEntity(BASEURL, ResponseDto.class).getBody();
        return responseDto.getData().toString();
    }


}
