package com.taskify.account.mapper;

import com.taskify.account.controller.dto.account.AccountCreationRequest;
import com.taskify.account.controller.dto.account.AccountResponse;
import com.taskify.account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account creationRequestToEntity(AccountCreationRequest request);

    AccountResponse entityToAccountResponse(Account account);
}
