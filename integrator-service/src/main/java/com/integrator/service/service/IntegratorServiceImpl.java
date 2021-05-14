package com.integrator.service.service;

import com.integrator.service.dto.TransactionDetailDto;
import com.integrator.service.dto.UserAccountDto;
import com.integrator.service.entity.AuditEventsEntity;
import com.integrator.service.entity.TransactionDetailEntity;
import com.integrator.service.entity.UserAccountEntity;
import com.integrator.service.repository.AuditEventRepository;
import com.integrator.service.repository.TransactionDetailRepository;
import com.integrator.service.repository.UserAccountRepository;
import com.integrator.service.util.IntegratorCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class IntegratorServiceImpl implements IntegratorService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    TransactionDetailRepository transactionDetailRepository;

    @Autowired
    AuditEventRepository auditEventRepository;

    // Use Try Catch

    @Override
    public String getAccountBalanceByAccountNo(Integer accountNo) {

        try {
            UserAccountEntity userAccountEntity = userAccountRepository.getAccountBalanceByAccountNo(accountNo);

            // check whether the accountNo exits
            if (userAccountEntity == null) {
                return IntegratorCommon.WRONGACCOUNT;
            } else {
                double totalRecevierAmt = transactionDetailRepository.getTransferredAmtByReceiver(accountNo);
                double totalSenderAmt = transactionDetailRepository.getTransferredAmtBySender(accountNo);
                double depositAmt = transactionDetailRepository.getTransferredAmtByDeposit(accountNo);

                return Double.toString((userAccountEntity.getBalanceAmount() + totalRecevierAmt + depositAmt) - totalSenderAmt);
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        //  return (userAccountEntity.getBalanceAmount() + userAccountRepository.getTransferredAmtByReceiver(accountNo) + userAccountRepository.getTransferredAmtByDeposit(accountNo)) - userAccountRepository.getTransferredAmtBySender(accountNo) ;
    }

    /**
     * get total amount from all account numbers for a specific user
     * Param userId
     * return total amount with the list of accounts details
     * */
    @Override
    public UserAccountDto getTotalAcctBalanceByUserId(String userId) {

        List<UserAccountEntity> userAccountEntity = userAccountRepository.findByUserId(userId);
        double accountBalance = 0;
        double totalAccountBalance = 0;
        List<Integer> accountList = new ArrayList<>();

        for (UserAccountEntity accountNos : userAccountEntity) {
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
    public String makeFundTransferOwnAccount(Integer receiverAcctNo, double depositedAmount) {

        UserAccountEntity userAccountEntity = userAccountRepository.getAccountBalanceByAccountNo(receiverAcctNo);
        // checks whether you have an account in the bank
        // deposits to the account and the balance needs to be updated
        // adds a record in the transaction table and also add a record in the userAccount table
        if (userAccountEntity == null) {
            return "You dont have a account!!";
        } else {
            TransactionDetailEntity transactionDetailEntity = transactionDetailRepository.findByReceiverAccountNo(receiverAcctNo);
            TransactionDetailDto transactionDetailDto = new TransactionDetailDto();
            transactionDetailDto.setReceiverAccountNo(transactionDetailEntity.getReceiverAccountNo());
            transactionDetailDto.setSenderAccountNo(transactionDetailEntity.getReceiverAccountNo());
            transactionDetailDto.setTransferredAmount(transactionDetailEntity.getTransferredAmount());
            transactionDetailDto.setDateTime(transactionDetailEntity.getDateTime());
            transactionDetailRepository.save(transactionDetailEntity);
            return transactionDetailDto.toString();
        }
    }

    @Override
    public String makeFundTransferToOtherAccount(Integer senderAcctNo, Integer receiverAcctNo, double depositedAmount) {
        TransactionDetailEntity transactionDetailEntity = transactionDetailRepository.findByReceiverAccountNo(receiverAcctNo);

        // must check whether user has a account first in order to send >> usertable by accountNo
        // checks whether you have money in account to transfer
        // if you transfer you the money you balance needed to be updated
        UserAccountEntity userAccountEntity = userAccountRepository.getAccountBalanceByAccountNo(senderAcctNo);

        if (userAccountEntity == null) {
            return "You dont have a account!!!";
        } else {
//            if(userAccountEntity.getBalanceAmount()>=depositedAmount) {
            transactionDetailEntity.setReceiverAccountNo(senderAcctNo);
            transactionDetailRepository.save(transactionDetailEntity);
            return null;
        }
    }

    @Override
    public void saveAPIForAudit(String apiName, String paramWithValue) {
        // saves API for Audit
        try {
            AuditEventsEntity auditEventsEntity = new AuditEventsEntity();
            auditEventsEntity.setApi_Name(apiName);
            auditEventsEntity.setParamsWithValue(paramWithValue);
            auditEventsEntity.setDateTime(Instant.now());
            auditEventRepository.save(auditEventsEntity);

        } catch (Exception e) {
            e.getMessage();
        }
    }


}
