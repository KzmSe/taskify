package com.taskify.account.controller.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taskify.account.entity.enums.AccountRole;
import com.taskify.account.entity.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private AccountStatus status;
    private AccountRole role;
}
