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
        // deposits to the transaction account and the balance needs to be updated
        // adds a record in the transaction table and also add a record in the userAccount table
        try {
            if (userAccountEntity == null) {
                return IntegratorCommon.ACCOUNT_DOESNT_EXISTS;
            } else {
            //  TransactionDetailEntity transactionDetailEntity = transactionDetailRepository.findBySenderAccountNo(receiverAcctNo);
                TransactionDetailEntity transactionDetailEntity = new TransactionDetailEntity();
                transactionDetailEntity.setSenderAccountNo(receiverAcctNo);
                transactionDetailEntity.setReceiverAccountNo(receiverAcctNo);
                transactionDetailEntity.setTransferredAmount(depositedAmount);
                transactionDetailEntity.setDateTime(Instant.now());
                transactionDetailRepository.save(transactionDetailEntity);

                // user account need to be updated with the transferred amount
               // userAccountEntity.setBalanceAmount(userAccountEntity.getBalanceAmount() + depositedAmount);
                // userAccountRepository.save(userAccountEntity);

                return "Deposited Successfully into your accountNo " + receiverAcctNo + "Balance Amount " + depositedAmount;
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

        if(Double.parseDouble(senders_balanceAmount)>=depositedAmount) {
            TransactionDetailEntity transactionEntity = new TransactionDetailEntity();
            transactionEntity.setSenderAccountNo(senderAcctNo);
            transactionEntity.setReceiverAccountNo(receiverAcctNo);
            transactionEntity.setTransferredAmount(depositedAmount);
            transactionEntity.setDateTime(Instant.now());
            transactionDetailRepository.save(transactionEntity);
            return "Transferred To Other Account Successfully";

        }
        else {
            return "You don't have sufficient amount in the account";
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
