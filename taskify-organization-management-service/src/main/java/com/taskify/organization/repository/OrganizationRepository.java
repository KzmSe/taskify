package com.taskify.organization.repository;

import com.taskify.organization.entity.Organization;
import com.taskify.organization.entity.enums.OrganizationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    boolean existsByIdAndStatus(Long organizationId, OrganizationStatus status);
}
