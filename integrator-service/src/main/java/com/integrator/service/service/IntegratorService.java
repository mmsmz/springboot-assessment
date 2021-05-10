package com.integrator.service.service;

import org.springframework.stereotype.Service;

@Service
public interface IntegratorService {
    double getAccountBalanceByAccountNo(Integer accountNo);
}
