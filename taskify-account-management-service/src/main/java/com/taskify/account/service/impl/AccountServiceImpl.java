package com.taskify.account.service.impl;

import com.taskify.account.client.OrganizationClient;
import com.taskify.account.client.dto.OrganizationResponse;
import com.taskify.account.controller.dto.account.AccountCreationRequest;
import com.taskify.account.controller.dto.account.AccountResponse;
import com.taskify.account.controller.dto.account.RegistrationRequest;
import com.taskify.account.entity.Account;
import com.taskify.account.entity.enums.AccountRole;
import com.taskify.account.entity.enums.AccountStatus;
import com.taskify.account.mapper.AccountMapper;
import com.taskify.account.repository.AccountRepository;
import com.taskify.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrganizationClient organizationClient;
    @Value("${account.default.password}")
    private String defaultPassword;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountResponse register(RegistrationRequest request) {
        Account account = new Account();
        account.setName(request.getAccountName());
        account.setSurname(request.getSurname());
        account.setEmail(request.getEmail());
        account.setPassword(passwordEncoder.encode(request.getPassword().trim()));
        account.setRole(AccountRole.ROLE_ADMIN);
        account.setStatus(AccountStatus.ACTIVE);

        Account savedAccount = accountRepository.save(account);
        AccountMapper.INSTANCE.entityToAccountResponse(savedAccount);

        ResponseEntity<OrganizationResponse> organization = organizationClient.create(request.getOrganization());
        organizationClient.assign(Objects.requireNonNull(organization.getBody()).getId(), account.getId());

        return savedAccount;
    }

    @Override
    public AccountResponse create(AccountCreationRequest request) {
        Account account = AccountMapper.INSTANCE.creationRequestToEntity(request);
        account.setPassword(passwordEncoder.encode(defaultPassword.trim()));
        account.setRole(AccountRole.ROLE_USER);
        account.setStatus(AccountStatus.ACTIVE);

        Account saveAccount = accountRepository.save(account);
        return AccountMapper.INSTANCE.entityToAccountResponse(saveAccount);
    }

    @Override
    public Boolean isAccountExist(Long accountId) {
        return accountRepository.existsByIdAndStatus(accountId, AccountStatus.ACTIVE);
    }
}
