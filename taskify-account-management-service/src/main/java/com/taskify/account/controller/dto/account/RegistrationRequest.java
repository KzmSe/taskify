package com.taskify.account.controller.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

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
    @Size(min = 6, max = 20)
    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])$",
    //message = "must contain at least one digit, one lower case, one upper case, one special character")
    private String password;
    @NotNull
    @Positive
    private Long organizationId;
}