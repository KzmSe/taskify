package com.taskify.organization.mapper;

import com.taskify.organization.client.dto.RegistrationRequest;
import com.taskify.organization.controller.dto.organization.OrganizationCreationRequest;
import com.taskify.organization.controller.dto.organization.OrganizationResponse;
import com.taskify.organization.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {

    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    Organization creationRequestToEntity(OrganizationCreationRequest request);

    OrganizationResponse entityToOrganizationResponse(Organization organization);

    @Mappings({
            @Mapping(source = "account.name", target = "name"),
            @Mapping(source = "account.surname", target = "surname"),
            @Mapping(source = "account.email", target = "email"),
            @Mapping(source = "account.password", target = "password")
    })
    RegistrationRequest organizationCreationRequestToRegistrationRequest(OrganizationCreationRequest request);
}
