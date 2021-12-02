package com.taskify.organization.service;

import com.taskify.organization.controller.dto.OrganizationCreationRequest;
import com.taskify.organization.controller.dto.OrganizationResponse;

public interface OrganizationService {

    OrganizationResponse create(OrganizationCreationRequest request);
}
