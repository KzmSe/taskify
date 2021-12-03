package com.taskify.organization.controller.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreationRequest {

    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String surname;
    @NotNull
    @Email
    private String email;
    @NotNull
    @NotEmpty
    @Min(6)
    private String password;
}