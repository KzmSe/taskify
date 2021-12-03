package com.taskify.organization.service.impl;

import com.taskify.organization.client.AccountClient;
import com.taskify.organization.controller.dto.organization.OrganizationCreationRequest;
import com.taskify.organization.controller.dto.organization.OrganizationResponse;
import com.taskify.organization.entity.Organization;
import com.taskify.organization.entity.enums.OrganizationStatus;
import com.taskify.organization.mapper.OrganizationMapper;
import com.taskify.organization.repository.OrganizationRepository;
import com.taskify.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final AccountClient accountClient;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, AccountClient accountClient) {
        this.organizationRepository = organizationRepository;
        this.accountClient = accountClient;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrganizationResponse create(OrganizationCreationRequest request) {
        var organization = OrganizationMapper.INSTANCE.creationRequestToEntity(request);
        organization.setStatus(OrganizationStatus.ACTIVE);

        var savedOrganization = organizationRepository.save(organization);
        var organizationResponse = OrganizationMapper.INSTANCE.entityToOrganizationResponse(savedOrganization);

        var registrationRequest = OrganizationMapper.INSTANCE.organizationCreationRequestToRegistrationRequest(request);
        registrationRequest.setOrganizationId(organizationResponse.getId());

        var accountResponse = accountClient.register(registrationRequest);
        return null;
    }

    @Override
    public Boolean isOrganizationExist(Long organizationId) {
        return organizationRepository.existsByIdAndStatus(organizationId, OrganizationStatus.ACTIVE);
    }
}
