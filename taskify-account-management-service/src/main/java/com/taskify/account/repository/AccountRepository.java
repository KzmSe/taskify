package com.taskify.account.repository;

import com.taskify.account.entity.Account;
import com.taskify.account.entity.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByIdAndStatus(Long accountId, AccountStatus status);

    Optional<Account> findByEmailAndStatus(String email, AccountStatus status);
}
