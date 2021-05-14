package com.integrator.service.repository;

import com.integrator.service.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity,Integer>{

    @Query(value = "SELECT * FROM user_account WHERE account_No=?1", nativeQuery = true)
    UserAccountEntity getAccountBalanceByAccountNo(Integer account);

    List<UserAccountEntity> findByUserId(String userId);

//    UserAccountEntity findByAccountNo(Integer accountNo);
}

