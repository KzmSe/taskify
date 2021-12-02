package com.taskify.organization.service.impl;

import com.taskify.organization.controller.dto.OrganizationCreationRequest;
import com.taskify.organization.controller.dto.OrganizationResponse;
import com.taskify.organization.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {


    @Override
    public OrganizationResponse create(OrganizationCreationRequest request) {
        return null;
    }
}
