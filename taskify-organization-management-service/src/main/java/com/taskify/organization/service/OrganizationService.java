package com.taskify.organization.service;

import com.taskify.organization.controller.dto.organization.OrganizationCreationRequest;
import com.taskify.organization.controller.dto.organization.OrganizationResponse;

public interface OrganizationService {

    OrganizationResponse create(OrganizationCreationRequest request);

    Boolean isOrganizationExist(Long organizationId);
}
