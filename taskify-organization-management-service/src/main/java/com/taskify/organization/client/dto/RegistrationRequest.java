package com.taskify.organization.client.dto;

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
    @Min(6)
    @Max(20)
    private String password;
    @NotNull
    @Positive
    private Long organizationId;
}