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
import java.util.ArrayList;
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
        String asd = "asd";
        UserAccountEntity userAccountEntity =  userAccountRepository.getAccountBalanceByAccountNo(accountNo);
//        double totalRecevierAmt =userAccountRepository.getTransferredAmtByReceiver(accountNo);
//        double totalSenderAmt =userAccountRepository.getTransferredAmtBySender(accountNo);
//        double depositAmt = userAccountRepository.getTransferredAmtByDeposit(accountNo);
//
//       return (userAccountEntity.getBalanceAmount() + totalRecevierAmt + depositAmt) - totalSenderAmt ;

        return (userAccountEntity.getBalanceAmount() + userAccountRepository.getTransferredAmtByReceiver(accountNo) + userAccountRepository.getTransferredAmtByDeposit(accountNo)) - userAccountRepository.getTransferredAmtBySender(accountNo) ;
    }

    @Override
    public UserAccountDto getTotalAcctBalanceByUserId(String userId) {

        List<UserAccountEntity> userAccountEntity = userAccountRepository.findByUserId(userId);
        double accountBalance =0;
        double totalAccountBalance = 0;
        List<Integer> accountList = new ArrayList<>();

        for (UserAccountEntity accountNos: userAccountEntity) {
            accountBalance = accountNos.getBalanceAmount();
            totalAccountBalance += accountBalance;
            accountList.add(accountNos.getAccountNo());
        }

        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setUserId(userId);
        userAccountDto.setListOfAccountNo(accountList);
        userAccountDto.setBalanceAmount(totalAccountBalance);

        return userAccountDto;
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
