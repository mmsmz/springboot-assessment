package com.integrator.service.controller;

//import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
public class IntegratorControllerTest {

        @InjectMocks
        IntegratorController integratorController;

        @Test
        public void  testGetAccountBalanceByAccountNo(){
            integratorController.getAccountBalanceByAccountNo(2147483647);
        }

        @Test(expected = NullPointerException.class)
        public void testingAPI(){
                integratorController.testingAPI();
        }

}
