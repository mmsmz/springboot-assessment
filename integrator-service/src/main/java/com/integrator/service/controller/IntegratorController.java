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
3. Make a fund transfer to own accounts
4. Make a fund transfer to other accounts
*/
    @Autowired
    private IntegratorService integratorService;

    @GetMapping("/getAccByAccountNo/{accNo}")
    public ResponseEntity<ResponseDto> getAccountBalanceByAccountNo(@PathVariable(name = "accNo") Integer accountNo){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setData(integratorService.getAccountBalanceByAccountNo(accountNo));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }



    @GetMapping("/getTotalAccountBalance")
    public String getTotalAccountBalance(){
        return "Hello World";
    }




}
