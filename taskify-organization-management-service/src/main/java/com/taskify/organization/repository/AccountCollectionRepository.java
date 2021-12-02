package com.taskify.organization.repository;

import com.taskify.organization.entity.OrganizationAccountCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountCollectionRepository extends JpaRepository<OrganizationAccountCollection, Long> {

}
