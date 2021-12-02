package com.taskify.account.service;

import com.taskify.account.controller.dto.account.AccountCreationRequest;
import com.taskify.account.controller.dto.account.AccountResponse;
import com.taskify.account.controller.dto.account.RegistrationRequest;

public interface AccountService {

    AccountResponse register(RegistrationRequest request);

    AccountResponse create(AccountCreationRequest request);

    Boolean assign(Long organizationId, Long accountId);

    Boolean isAccountExist(Long accountId);
}
