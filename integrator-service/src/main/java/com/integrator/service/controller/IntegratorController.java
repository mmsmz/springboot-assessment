package com.integrator.service.controller;

import com.integrator.service.dto.ResponseDto;
import com.integrator.service.service.IntegratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "integratorService")
public class IntegratorController {

   /*
    1. Get account balance of particular account
    2. Get total account balance
    3. Make a fund transfer to own accounts ->> only receiversaaccount + service will add to receiver as well.
    4. Make a fund transfer to other accounts ->> sender==myaccount and the recieversAccount
   */

    @Autowired
    private IntegratorService integratorService;

    @GetMapping("/getAccountBalanceByAccountNo/{accountNo}")
    public ResponseEntity<ResponseDto> getAccountBalanceByAccountNo(@PathVariable Integer accountNo){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setData(integratorService.getAccountBalanceByAccountNo(accountNo));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/makeFundTransferOwnAccount")
    public ResponseEntity<ResponseDto> makeFundTransferToOwnAccount(@RequestParam Integer receiverAcctNo,
                                                                  @RequestParam double  depositedAmount){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setData(integratorService.makeFundTransferOwnAccount(receiverAcctNo, depositedAmount));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/makeFundTransferToOtherAccount")
    public ResponseEntity<ResponseDto> makeFundTransferToOtherAccount(@RequestParam Integer senderAcctNo,
                                                                      @RequestParam Integer receiverAcctNo,
                                                                    @RequestParam double  depositedAmount){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setData(integratorService.makeFundTransferToOtherAccount(senderAcctNo, receiverAcctNo, depositedAmount));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @GetMapping("/test")
    public String testingAPI(){
        return "Hello World";
    }

}
