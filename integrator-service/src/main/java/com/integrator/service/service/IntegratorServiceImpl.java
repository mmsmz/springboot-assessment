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
    public  double  getAccountBalanceByAccountNo(Integer accountNo) {
        UserAccountEntity userAccountEntity =  userAccountRepository.getAccountBalanceByAccountNo(accountNo);
        double totalRecevierAmt =userAccountRepository.getTransferredAmtByReceiver(accountNo);
        double totalSenderAmt =userAccountRepository.getTransferredAmtBySender(accountNo);
        double depositAmt = userAccountRepository.getTransferredAmtByDeposit(accountNo);
       return (userAccountEntity.getBalanceAmount() + totalRecevierAmt + depositAmt) - totalSenderAmt ;
    }
}
