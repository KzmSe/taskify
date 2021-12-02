package com.taskify.organization.service.impl;

import com.taskify.organization.client.AccountClient;
import com.taskify.organization.controller.dto.OrganizationCreationRequest;
import com.taskify.organization.controller.dto.OrganizationResponse;
import com.taskify.organization.entity.Organization;
import com.taskify.organization.entity.OrganizationAccountCollection;
import com.taskify.organization.entity.enums.OrganizationStatus;
import com.taskify.organization.exception.DataNotFoundException;
import com.taskify.organization.exception.response.ResponseMessage;
import com.taskify.organization.mapper.OrganizationMapper;
import com.taskify.organization.repository.AccountCollectionRepository;
import com.taskify.organization.repository.OrganizationRepository;
import com.taskify.organization.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final AccountCollectionRepository accountCollectionRepository;
    private final AccountClient accountClient;

    @Override
    public OrganizationResponse create(OrganizationCreationRequest request) {
        Organization organization = OrganizationMapper.INSTANCE.creationRequestToEntity(request);
        organization.setStatus(OrganizationStatus.ACTIVE);

        Organization savedOrganization = organizationRepository.save(organization);
        return OrganizationMapper.INSTANCE.entityToOrganizationResponse(savedOrganization);
    }

    @Override
    public Boolean assign(Long organizationId, Long accountId) {
        boolean isOrganizationExist = organizationRepository.existsByIdAndStatus(organizationId, OrganizationStatus.ACTIVE);
        if (!isOrganizationExist) {
            throw new DataNotFoundException(ResponseMessage.ERROR_ORGANIZATION_NOT_FOUND_BY_ID);
        }

        ResponseEntity<Boolean> isAccountExist = accountClient.isExistByIdAndStatus(accountId);
        if (isAccountExist.getStatusCodeValue() != HttpStatus.OK.value() || Boolean.FALSE.equals(isAccountExist.getBody())) {
            throw new DataNotFoundException(ResponseMessage.ERROR_ACCOUNT_NOT_FOUND_BY_ID);
        }

        OrganizationAccountCollection collection = new OrganizationAccountCollection();
        Organization organization = new Organization();
        organization.setId(organizationId);
        collection.setOrganization(organization);
        collection.setAccountId(accountId);

        OrganizationAccountCollection savedCollection = accountCollectionRepository.save(collection);
        return savedCollection.getId() != null;
    }
}
