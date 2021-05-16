package com.dummycorebanking.service.controller;

import com.dummycorebanking.service.dto.ResponseDto;
import com.dummycorebanking.service.service.CoreBankService;
import com.dummycorebanking.service.util.CoreBankCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;


@RestController
@RequestMapping(name = "coreBank")
public class CoreBankController {

   /*
    1. Get account balance of particular account
    2. Get total account balance for a specificUser
    3. Make a fund transfer to own accounts ->> only receiversaaccountNo + service will add to receiver as well.
    4. Make a fund transfer to other accounts ->> sender==myaccountNo and the recieversAccount
   */

    @Autowired
    private CoreBankService coreBankService;

    @GetMapping(CoreBankCommon.GET_ACCT_BALANCE_BY_ACCTNO)
    public ResponseEntity<ResponseDto> getAccountBalanceByAccountNo(@RequestParam Integer accountNo){

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(CoreBankCommon.SUCCESS);
        responseDto.setData(coreBankService.getAccountBalanceByAccountNo(accountNo));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @GetMapping(CoreBankCommon.GET_TOTALACCT_BALANCE_BY_USERID)
    public ResponseEntity<ResponseDto> getTotalAcctBalanceByUserId(@RequestParam String userId){

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(CoreBankCommon.SUCCESS);
        responseDto.setData(coreBankService.getTotalAcctBalanceByUserId(userId)); // 0.0
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping(CoreBankCommon.MAKE_FUND_TRANSFER_TO_OWN_ACCT)
    public ResponseEntity<ResponseDto> makeFundTransferToOwnAccount(@RequestParam Integer receiverAcctNo,
                                                                    @RequestParam Double  depositedAmount) throws URISyntaxException {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(CoreBankCommon.SUCCESS);
        responseDto.setData(coreBankService.makeFundTransferOwnAccount(receiverAcctNo, depositedAmount));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @PostMapping(CoreBankCommon.MAKE_FUND_TRANSFER_TO_OTHER_ACCT)
    public ResponseEntity<ResponseDto> makeFundTransferToOtherAccount(@RequestParam Integer senderAcctNo,
                                                                      @RequestParam Integer receiverAcctNo,
                                                                    @RequestParam Double  depositedAmount){

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(CoreBankCommon.SUCCESS);
        responseDto.setData(coreBankService.makeFundTransferToOtherAccount(senderAcctNo, receiverAcctNo, depositedAmount));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

//    @GetMapping("/test")
//    public String testingAPI(){
//        return "Hello World";
//    }

}
