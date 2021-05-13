package com.integrator.service.service;

import com.integrator.service.entity.UserAccountEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntegratorService {
    List<UserAccountEntity> getAccountBalanceByAccountNo(Integer accountNo);
//    double getAccountBalanceByAccountNo(Integer accountNo);

}
