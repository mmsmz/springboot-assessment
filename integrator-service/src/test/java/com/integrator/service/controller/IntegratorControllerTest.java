package com.integrator.service.controller;

import com.integrator.service.service.IntegratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
public class IntegratorControllerTest {

        @InjectMocks
        IntegratorController integratorController;

        @Test
        public void  testGetAccountBalanceByAccountNo(){
           // Mockito.when(integratorService.getAccountBalanceByAccountNo(456789123)).thenReturn(45678d);
            integratorController.getAccountBalanceByAccountNo(2147483647);
        }

        @Test
        public void getTotalAcctBalanceByUserId(){
                integratorController.getTotalAcctBalanceByUserId("111");
        }

        @Test
        public void makeFundTransferToOwnAccount(){
                integratorController.makeFundTransferToOwnAccount(456123,132456135);
        }

        @Test
        public void makeFundTransferToOtherAccount(){
                integratorController.makeFundTransferToOtherAccount(123456789,456123789,132456135);
        }
}
