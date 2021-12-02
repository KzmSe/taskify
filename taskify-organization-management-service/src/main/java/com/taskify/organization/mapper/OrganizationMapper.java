package com.taskify.organization.mapper;

import com.taskify.organization.controller.dto.organization.OrganizationCreationRequest;
import com.taskify.organization.controller.dto.organization.OrganizationResponse;
import com.taskify.organization.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {

    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    Organization creationRequestToEntity(OrganizationCreationRequest request);

    OrganizationResponse entityToOrganizationResponse(Organization organization);
}
