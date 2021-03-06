package com.taskify.account.controller;

import com.taskify.account.controller.dto.account.AccountCreationRequest;
import com.taskify.account.controller.dto.account.AccountResponse;
import com.taskify.account.controller.dto.account.RegistrationRequest;
import com.taskify.account.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "Operations related to organization in Taskify Account Management Service")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts/default")
    @ApiOperation(value = "Create a new default account for the organization")
    public ResponseEntity<AccountResponse> createDefault(@Valid @RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(accountService.createDefault(request));
    }

    @PostMapping("/accounts")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Create a new account")
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountCreationRequest request) {
        return ResponseEntity.ok(accountService.create(request));
    }

    @PostMapping("/accounts/{accountId}/organizations/{organizationId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Assign account to organization")
    public ResponseEntity<Boolean> assign(@PathVariable(name = "accountId") Long accountId,
                                          @PathVariable(name = "organizationId") Long organizationId) {
        return ResponseEntity.ok(accountService.assign(organizationId, accountId));
    }

    @GetMapping("/accounts/{accountId}/existence")
    @ApiOperation(value = "Check account with given id is exist")
    public ResponseEntity<Boolean> isAccountExist(@PathVariable(name = "accountId") Long accountId) {
        return ResponseEntity.ok(accountService.isAccountExist(accountId));
    }
}
