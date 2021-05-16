package com.integrator.service.service;

import com.integrator.service.dto.UserAccountDto;
import com.integrator.service.entity.AuditEventsEntity;
import com.integrator.service.entity.TransactionDetailEntity;
import com.integrator.service.entity.UserAccountEntity;
import com.integrator.service.repository.AuditEventRepository;
import com.integrator.service.repository.TransactionDetailRepository;
import com.integrator.service.repository.UserAccountRepository;
import com.integrator.service.util.IntegratorCommon;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

@Service
@Transactional
public class IntegratorServiceImpl implements IntegratorService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    TransactionDetailRepository transactionDetailRepository;

    @Autowired
    AuditEventRepository auditEventRepository;

    @Override
    public String getAccountBalanceByAccountNo(Integer accountNo) {

        try {
            UserAccountEntity userAccountEntity = userAccountRepository.getAccountBalanceByAccountNo(accountNo);

            // check whether the accountNo exits
            if (userAccountEntity == null) {
                return IntegratorCommon.WRONGACCOUNT;
            } else {
                // Note: we can directly return without creating object, i have apply to make it clear for understanding
                double totalRecevierAmt = transactionDetailRepository.getTransferredAmtByReceiver(accountNo);
                double totalSenderAmt = transactionDetailRepository.getTransferredAmtBySender(accountNo);

                // own account depositAmt
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
     * return total Balance from number of accounts based on the userId
     * */
    @Override
    public double getTotalAcctBalanceByUserId(String userId) {

       List<Integer> list = userAccountRepository.getAccountNosByUserId(userId);
       double finalResult = 0;
       for (Integer totalBalance : list){
           finalResult = finalResult + Double.parseDouble(getAccountBalanceByAccountNo(totalBalance));
       }
        return finalResult;
    }


    @Override
    public String makeFundTransferOwnAccount(Integer receiverAcctNo, double depositedAmount) {

        UserAccountEntity userAccountEntity = userAccountRepository.getAccountBalanceByAccountNo(receiverAcctNo);
        // checks whether you have an account in the bank
        // deposits to the transaction account and the balance needs to be updated
        // adds a record in the transaction table and also add a record in the userAccount table
        try {
            if (userAccountEntity == null) {
                return IntegratorCommon.ACCOUNT_DOESNT_EXISTS;
            } else {
                TransactionDetailEntity transactionDetailEntity = new TransactionDetailEntity();
                transactionDetailEntity.setSenderAccountNo(receiverAcctNo);
                transactionDetailEntity.setReceiverAccountNo(receiverAcctNo);
                transactionDetailEntity.setTransferredAmount(depositedAmount);
                transactionDetailEntity.setDateTime(Instant.now());
                transactionDetailRepository.save(transactionDetailEntity);

                return "Deposited Successfully into your accountNo = " + receiverAcctNo + " & Deposited Amount = " + depositedAmount;
            }
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @Override
    public String makeFundTransferToOtherAccount(Integer senderAcctNo, Integer receiverAcctNo, double depositedAmount) {

        // don't need to check for account availability
        // checks whether you have money in account to transfer
        String senders_balanceAmount = getAccountBalanceByAccountNo(senderAcctNo);

        if(depositedAmount<=Double.parseDouble(senders_balanceAmount)) {
            TransactionDetailEntity transactionEntity = new TransactionDetailEntity();
            transactionEntity.setSenderAccountNo(senderAcctNo);
            transactionEntity.setReceiverAccountNo(receiverAcctNo);
            transactionEntity.setTransferredAmount(depositedAmount);
            transactionEntity.setDateTime(Instant.now());
            transactionDetailRepository.save(transactionEntity);
            return IntegratorCommon.TRANSFERRED_SUCCESSFULLY;

        }
        else {
            return IntegratorCommon.INSUFFICIENT_ACCT_BALANCE;
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
