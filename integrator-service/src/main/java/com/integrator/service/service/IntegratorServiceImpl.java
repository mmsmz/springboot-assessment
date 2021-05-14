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

            if (userAccountEntity == null) {
                return IntegratorCommon.WRONGACCOUNT;
            } else {
                double totalRecevierAmt = transactionDetailRepository.getTransferredAmtByReceiver(accountNo);
                double totalSenderAmt = transactionDetailRepository.getTransferredAmtBySender(accountNo);
                double depositAmt = transactionDetailRepository.getTransferredAmtByDeposit(accountNo);

                // saves API for Audit
                AuditEventsEntity auditEventsEntity = new AuditEventsEntity();
                auditEventsEntity.setApi_Name(IntegratorCommon.GET_ACCT_BALANCE_BY_ACCTNO);
                auditEventsEntity.setParamsWithValue("accountNo = " + accountNo);
                auditEventsEntity.setDateTime(Instant.now());
                auditEventRepository.save(auditEventsEntity);

                return Double.toString((userAccountEntity.getBalanceAmount() + totalRecevierAmt + depositAmt) - totalSenderAmt);
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        //  return (userAccountEntity.getBalanceAmount() + userAccountRepository.getTransferredAmtByReceiver(accountNo) + userAccountRepository.getTransferredAmtByDeposit(accountNo)) - userAccountRepository.getTransferredAmtBySender(accountNo) ;
    }

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
    public TransactionDetailDto makeFundTransferOwnAccount(Integer receiverAcctNo, double depositedAmount) {

//        TransactionDetailEntity transactionDetailEntity = transactionDetailRepository.findByReceiverAccountNo(receiverAcctNo);
//        TransactionDetailDto transactionDetailDto = new TransactionDetailDto();
//        transactionDetailDto.setReceiverAccountNo(transactionDetailEntity.getReceiverAccountNo());
//        transactionDetailDto.setSenderAccountNo(transactionDetailEntity.getReceiverAccountNo());
//        transactionDetailDto.setTransferredAmount(transactionDetailEntity.getTransferredAmount());
//        transactionDetailDto.setDateTime(transactionDetailEntity.getDateTime());
//        transactionDetailRepository.save(transactionDetailEntity);
//
//        return transactionDetailDto;
        return null;

    }

    @Override
    public TransactionDetailDto makeFundTransferToOtherAccount(Integer senderAcctNo, Integer receiverAcctNo, double depositedAmount) {
        //   TransactionDetailEntity transactionDetailEntity = transactionDetailRepository.findByReceiverAccountNo(receiverAcctNo);
        return null;
    }
}
