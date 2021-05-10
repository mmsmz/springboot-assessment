package com.integrator.service.service;

import com.integrator.service.dto.ResponseDto;
import com.integrator.service.dto.TransactionDetailDto;
import com.integrator.service.dto.UserAccountDto;
import com.integrator.service.entity.TransactionDetailEntity;
import com.integrator.service.entity.UserAccountEntity;
import com.integrator.service.repository.TransactionDetailRepository;
import com.integrator.service.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntegratorServiceImpl implements IntegratorService{

    @Autowired
    UserAccountRepository  userAccountRepository;

    @Autowired
    TransactionDetailRepository transactionDetailRepository;


    @Override
    public double getAccountBalanceByAccountNo(Integer accountNo) {

        UserAccountEntity userAccountEntity = userAccountRepository.findByAccountNo(accountNo);
        UserAccountDto userAccountDto = new UserAccountDto();
//      userAccountDto.setBalanceAmount(userAccountEntity.getBalanceAmount());

        TransactionDetailEntity transSenderEntity  =  transactionDetailRepository.findBySenderAccountNo(accountNo);
        TransactionDetailEntity transReceiverEntity  =  transactionDetailRepository.findByReceiverAccountNo(accountNo);

//        userAccountEntity.getBalanceAmount() +
//        (ini + rece ) - sender
//        (ini + sql + sender)

        return userAccountDto.getBalanceAmount();
    }
}
