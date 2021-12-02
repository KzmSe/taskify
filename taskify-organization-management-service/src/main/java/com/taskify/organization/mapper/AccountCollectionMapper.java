package com.taskify.organization.mapper;

import com.taskify.organization.entity.Organization;
import com.taskify.organization.entity.OrganizationAccountCollection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountCollectionMapper {

    AccountCollectionMapper INSTANCE = Mappers.getMapper(AccountCollectionMapper.class);

    @Mapping(target = "country", source = "country", qualifiedByName = "requestToEntity")
    OrganizationAccountCollection assignRequestToEntity(Long organizationId, Long accountId);

    @Named("requestToEntity")
    static OrganizationAccountCollection requestToEntity(Long organizationId, Long accountId) {
        OrganizationAccountCollection collection = new OrganizationAccountCollection();
        Organization organization = new Organization();
        organization.setId(organizationId);
        collection.setOrganization(organization);
        collection.setAccountId(accountId);
        return collection;
    }
}
