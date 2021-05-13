package com.integrator.service.controller;

import com.integrator.service.dto.ResponseDto;
import com.integrator.service.service.IntegratorService;
import com.integrator.service.util.IntegratorCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "integratorService")
public class IntegratorController {

   /*
    1. Get account balance of particular account
    2. Get total account balance for a specificUser
    3. Make a fund transfer to own accounts ->> only receiversaaccountNo + service will add to receiver as well.
    4. Make a fund transfer to other accounts ->> sender==myaccountNo and the recieversAccount
   */

    @Autowired
    private IntegratorService integratorService;

    @GetMapping("/getAccountBalanceByAccountNo/{accountNo}")
    public ResponseEntity<ResponseDto> getAccountBalanceByAccountNo(@PathVariable Integer accountNo){
        String asd = "ASd";
        String as = "ASd";
        String as1d = "ASd";
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(IntegratorCommon.SUCCESS);
        responseDto.setData(integratorService.getAccountBalanceByAccountNo(accountNo));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getTotalAcctBalanceByUserId/{userId}")
    public ResponseEntity<ResponseDto> getTotalAcctBalanceByUserId(@PathVariable String userId){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(IntegratorCommon.SUCCESS);
//      responseDto.setData();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/makeFundTransferOwnAccount")
    public ResponseEntity<ResponseDto> makeFundTransferToOwnAccount(@RequestParam Integer receiverAcctNo,
                                                                  @RequestParam double  depositedAmount){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(IntegratorCommon.SUCCESS);
        responseDto.setData(integratorService.makeFundTransferOwnAccount(receiverAcctNo, depositedAmount));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/makeFundTransferToOtherAccount")
    public ResponseEntity<ResponseDto> makeFundTransferToOtherAccount(@RequestParam Integer senderAcctNo,
                                                                      @RequestParam Integer receiverAcctNo,
                                                                    @RequestParam double  depositedAmount){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(IntegratorCommon.SUCCESS);
        responseDto.setData(integratorService.makeFundTransferToOtherAccount(senderAcctNo, receiverAcctNo, depositedAmount));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @GetMapping("/test")
    public String testingAPI(String asd){
        String asdasd = "4898790";
        String asdaasdsd = "4898790";
        String asdssasd = "4898790";
        asd = "Hello World";
        return asd;
    }

}
