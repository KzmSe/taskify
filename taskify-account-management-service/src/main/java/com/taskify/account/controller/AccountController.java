package com.taskify.account.controller;

import com.taskify.account.controller.dto.account.AccountCreationRequest;
import com.taskify.account.controller.dto.account.AccountResponse;
import com.taskify.account.controller.dto.account.RegistrationRequest;
import com.taskify.account.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/registration")
    @ApiOperation(value = "Create a new organization account")
    public ResponseEntity<AccountResponse> register(@Valid @RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(accountService.register(request));
    }

    @PostMapping("/accounts")
    @ApiOperation(value = "Create a new account")
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountCreationRequest request) {
        return ResponseEntity.ok(accountService.create(request));
    }

    @GetMapping("/accounts/{accountId}/existence")
    @ApiOperation(value = "Check account with given id is exist")
    public ResponseEntity<Boolean> isAccountExist(@PathVariable(name = "accountId") Long accountId) {
        return ResponseEntity.ok(accountService.isAccountExist(accountId));
    }
}
