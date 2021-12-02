package com.taskify.organization.controller.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountCollectionRequest {

    @NotNull
    @Positive
    private Long organizationId;
    @NotNull
    @Positive
    private String accountId;
}
