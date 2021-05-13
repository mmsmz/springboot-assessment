package com.integrator.service.service;
import com.integrator.service.entity.UserAccountEntity;
import com.integrator.service.repository.TransactionDetailRepository;
import com.integrator.service.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IntegratorServiceImpl implements IntegratorService{

    @Autowired
    UserAccountRepository  userAccountRepository;

    @Autowired
    TransactionDetailRepository transactionDetailRepository;


    @Override
    public  List<UserAccountEntity>  getAccountBalanceByAccountNo(Integer accountNo) {
        List<UserAccountEntity> userAccEntityList = userAccountRepository.getAccountBalanceByAccountNo(accountNo);
//        UserAccountDto userAccountDto = new UserAccountDto();
//        userAccountDto.setUserId(userAccountEntity.getBalanceAmount());
        return userAccountRepository.getAccountBalanceByAccountNo(accountNo);
    }


}
