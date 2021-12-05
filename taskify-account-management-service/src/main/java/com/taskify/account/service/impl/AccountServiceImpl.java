package com.taskify.account.service.impl;

import com.taskify.account.controller.dto.account.AccountCreationRequest;
import com.taskify.account.controller.dto.account.AccountResponse;
import com.taskify.account.controller.dto.account.RegistrationRequest;
import com.taskify.account.entity.Account;
import com.taskify.account.entity.AccountOrganizationCollection;
import com.taskify.account.entity.enums.AccountRole;
import com.taskify.account.entity.enums.AccountStatus;
import com.taskify.account.exception.DataNotFoundException;
import com.taskify.account.exception.response.ResponseMessage;
import com.taskify.account.mapper.AccountMapper;
import com.taskify.account.repository.AccountRepository;
import com.taskify.account.repository.OrganizationCollectionRepository;
import com.taskify.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrganizationCollectionRepository organizationCollectionRepository;
    @Value("${account.default.password}")
    private String defaultPassword;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder, OrganizationCollectionRepository organizationCollectionRepository) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.organizationCollectionRepository = organizationCollectionRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountResponse register(RegistrationRequest request) {
        var account = AccountMapper.INSTANCE.registrationRequestToEntity(request);
        account.setPassword(passwordEncoder.encode(request.getPassword().trim()));
        account.setRole(AccountRole.ROLE_ADMIN);
        account.setStatus(AccountStatus.ACTIVE);

        var savedAccount = accountRepository.save(account);
        var accountResponse = AccountMapper.INSTANCE.entityToAccountResponse(savedAccount);

        var isAssigned = assign(request.getOrganizationId(), savedAccount.getId());

        if (!isAssigned) {
            //throw new Exception(ResponseMessage.ERROR_INTERNAL_SERVER_ERROR);
        }

        return null;
    }

    @Override
    public AccountResponse create(AccountCreationRequest request) {
        var account = AccountMapper.INSTANCE.creationRequestToEntity(request);
        account.setPassword(passwordEncoder.encode(defaultPassword.trim()));
        account.setRole(AccountRole.ROLE_USER);
        account.setStatus(AccountStatus.ACTIVE);

        var saveAccount = accountRepository.save(account);
        return AccountMapper.INSTANCE.entityToAccountResponse(saveAccount);
    }

    @Override
    public Boolean assign(Long organizationId, Long accountId) {
        var isAccountExist = accountRepository.existsByIdAndStatus(accountId, AccountStatus.ACTIVE);
        if (!isAccountExist) {
            throw new DataNotFoundException(ResponseMessage.ERROR_ACCOUNT_NOT_FOUND_BY_ID);
        }

        var collection = new AccountOrganizationCollection();
        collection.setOrganizationId(organizationId);
        var account = new Account();
        account.setId(accountId);
        collection.setAccount(account);

        var savedCollection = organizationCollectionRepository.save(collection);
        return savedCollection.getId() != null;
    }

    @Override
    public Boolean isAccountExist(Long accountId) {
        return accountRepository.existsByIdAndStatus(accountId, AccountStatus.ACTIVE);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var optionalAccount = accountRepository.findByEmailAndStatus(s, AccountStatus.ACTIVE);
        optionalAccount.orElseThrow(() -> new DataNotFoundException(ResponseMessage.ERROR_ACCOUNT_NOT_FOUND_BY_EMAIL));

        return new User(
                optionalAccount.get().getUsername(),
                optionalAccount.get().getPassword(),
                optionalAccount.get().getAuthorities()
        );
    }
}
