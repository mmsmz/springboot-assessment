package com.integrator.service.controller;

import com.integrator.service.dto.ResponseDto;
import com.integrator.service.dto.UserAccountDto;
import com.integrator.service.service.IntegratorService;
import com.integrator.service.service.IntegratorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
public class IntegratorControllerTest {

        @InjectMocks
        IntegratorController integratorController;
        @Mock
        IntegratorServiceImpl integratorService;
        @Test
        public void  testGetAccountBalanceByAccountNo(){
//                ResponseDto responseDto = Mockito.mock(ResponseDto.class);
//                responseDto.setData(2222);
            Mockito.when(integratorService.getAccountBalanceByAccountNo(456789123)).thenReturn("45645d");
            integratorController.getAccountBalanceByAccountNo(2147483647);
        }

        @Test
        public void getTotalAcctBalanceByUserId(){
                UserAccountDto userAccountDto = Mockito.mock(UserAccountDto.class);
                Mockito.when(integratorService.getTotalAcctBalanceByUserId("456789123")).thenReturn(userAccountDto);
                integratorController.getTotalAcctBalanceByUserId("111");

        }

        @Test
        public void makeFundTransferToOwnAccount(){
                integratorController.makeFundTransferToOwnAccount(456123,132456135d);
        }

        @Test
        public void makeFundTransferToOtherAccount(){
                integratorController.makeFundTransferToOtherAccount(123456789,456123789,132456135d);
        }
}
