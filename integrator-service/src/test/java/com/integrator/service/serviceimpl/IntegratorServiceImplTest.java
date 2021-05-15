package com.integrator.service.serviceimpl;

import com.integrator.service.entity.UserAccountEntity;
import com.integrator.service.repository.UserAccountRepository;
import com.integrator.service.service.IntegratorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class IntegratorServiceImplTest {

    @InjectMocks
    IntegratorServiceImpl integratorService;

    @Mock
    UserAccountRepository userAccountRepository = spy(UserAccountRepository.class);

    @Test
    public void testGetAccountBalanceByAccountNo(){
       // UserAccountEntity userAccountEntity =  userAccountRepository.getAccountBalanceByAccountNo(accountNo);
        Integer accountNo = 123456789;
        UserAccountEntity userAccountEntity = spy(UserAccountEntity.class);
        userAccountEntity.setUserId("111");
        userAccountEntity.setAccountNo(123456789);
        userAccountEntity.setBalanceAmount(85000d);

        Mockito.when(userAccountRepository.getAccountBalanceByAccountNo(accountNo)).thenReturn(userAccountEntity);

//      Mockito.when(userAccountRepository.getTransferredAmtByReceiver(accountNo)).thenReturn((double) 100);
//      Mockito.when(userAccountRepository.getTransferredAmtBySender(accountNo)).thenReturn((double) 200);
//      Mockito.when(userAccountRepository.getTransferredAmtByDeposit(accountNo)).thenReturn((double) 300);
        integratorService.getAccountBalanceByAccountNo(accountNo);
    }

}
