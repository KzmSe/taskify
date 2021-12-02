package com.taskify.organization.controller.dto.organization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taskify.organization.entity.enums.OrganizationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationResponse {

    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private OrganizationStatus status;
}
