package com.taskify.account.repository;

import com.taskify.account.entity.AccountOrganizationCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationCollectionRepository extends JpaRepository<AccountOrganizationCollection, Long> {

}
