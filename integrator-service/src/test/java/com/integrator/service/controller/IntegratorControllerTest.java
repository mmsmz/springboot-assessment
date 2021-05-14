package com.integrator.service.controller;

//import static org.junit.Assert.*;


import com.integrator.service.dto.ResponseDto;
import com.integrator.service.service.IntegratorService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
public class IntegratorControllerTest {

        @InjectMocks
        IntegratorController integratorController;

        @Mock
        IntegratorService integratorService;

        @Test
        public void  testGetAccountBalanceByAccountNo(){
           // Mockito.when(integratorService.getAccountBalanceByAccountNo(456789123)).thenReturn(45678d);
            integratorController.getAccountBalanceByAccountNo(2147483647);
        }

        @Test
        public void getTotalAcctBalanceByUserId(){
//                Mockito.when(integratorService.getAccountBalanceByAccountNo(456789123)).thenReturn(45678d);
                integratorController.getTotalAcctBalanceByUserId("111");
        }

        @Test
        public void makeFundTransferToOwnAccount(){
//                Mockito.when(integratorService.getAccountBalanceByAccountNo(456789123)).thenReturn(45678d);
                integratorController.makeFundTransferToOwnAccount(456123,132456135);
        }

        @Test
        public void makeFundTransferToOtherAccount(){
//                Mockito.when(integratorService.getAccountBalanceByAccountNo(456789123)).thenReturn(45678d);
                integratorController.makeFundTransferToOtherAccount(123456789,456123789,132456135);
        }
}
