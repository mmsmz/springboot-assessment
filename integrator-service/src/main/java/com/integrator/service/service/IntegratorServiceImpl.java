package com.integrator.service.service;

import com.integrator.service.dto.TransactionDetailDto;
import com.integrator.service.dto.UserAccountDto;
import com.integrator.service.entity.TransactionDetailEntity;
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

    // Use Try Catch

    @Override
    public  double  getAccountBalanceByAccountNo(Integer accountNo) {
        UserAccountEntity userAccountEntity =  userAccountRepository.getAccountBalanceByAccountNo(accountNo);
        double totalRecevierAmt =userAccountRepository.getTransferredAmtByReceiver(accountNo);
        double totalSenderAmt =userAccountRepository.getTransferredAmtBySender(accountNo);
        double depositAmt = userAccountRepository.getTransferredAmtByDeposit(accountNo);

       return (userAccountEntity.getBalanceAmount() + totalRecevierAmt + depositAmt) - totalSenderAmt ;
    }

    @Override
    public UserAccountDto getTotalAcctBalanceByUserId(String userId) {

        List<UserAccountEntity> userAccountEntity = userAccountRepository.findByUserId(userId);



        return null;
    }


    @Override
    public TransactionDetailDto makeFundTransferOwnAccount(Integer receiverAcctNo,  double  depositedAmount) {

        TransactionDetailEntity transactionDetailEntity = transactionDetailRepository.findByReceiverAccountNo(receiverAcctNo);
        TransactionDetailDto transactionDetailDto = new TransactionDetailDto();
        transactionDetailDto.setReceiverAccountNo(transactionDetailEntity.getReceiverAccountNo());
        transactionDetailDto.setSenderAccountNo(transactionDetailEntity.getReceiverAccountNo());
        transactionDetailDto.setTransferredAmount(transactionDetailEntity.getTransferredAmount());
        transactionDetailDto.setDateTime(transactionDetailEntity.getDateTime());
        transactionDetailRepository.save(transactionDetailEntity);

        return transactionDetailDto;

    }

    @Override
    public TransactionDetailDto makeFundTransferToOtherAccount(Integer senderAcctNo, Integer receiverAcctNo, double depositedAmount) {
        TransactionDetailEntity transactionDetailEntity = transactionDetailRepository.findByReceiverAccountNo(receiverAcctNo);
        return null;
    }
}
