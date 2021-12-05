package com.taskify.organization.service.impl

import com.taskify.organization.client.AccountClient
import com.taskify.organization.client.dto.AccountResponse
import com.taskify.organization.controller.dto.account.AccountCreationRequest
import com.taskify.organization.controller.dto.organization.OrganizationCreationRequest
import com.taskify.organization.controller.dto.organization.OrganizationResponse
import com.taskify.organization.entity.Organization
import com.taskify.organization.entity.enums.OrganizationStatus
import com.taskify.organization.repository.OrganizationRepository
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class OrganizationServiceImplTest extends Specification {

    def organizationRepository
    def accountClient
    def enhancedRandom
    def organizationService

    void setup() {
        organizationRepository = Mock(OrganizationRepository) as OrganizationRepository
        accountClient = Mock(AccountClient) as AccountClient
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandom()
        organizationService = new OrganizationServiceImpl(organizationRepository, accountClient)
    }

    def "Create should return OrganizationResponse if saves organization successfully"() {
        given: "OrganizationCreationRequest"
        def request = enhancedRandom.nextObject(OrganizationCreationRequest)
        def response = enhancedRandom.nextObject(Organization)
        def clientResponse = enhancedRandom.nextObject(AccountResponse)
        request.account.password = 'defaultPassword'

        when: "calls method with the request"
        def result = organizationService.create(request)

        then: "result should not be null"
        result != null
        result.id != null

        1 * organizationRepository.save(_) >> response
        1 * accountClient.createDefault(_) >> clientResponse
        0 * _._
    }

    def "IsOrganizationExist should return true if organization with given organization id is exist"() {
        given: "organization id"
        def organizationId = 1L
        def status = OrganizationStatus.ACTIVE

        when: "calls method with the id"
        def result = organizationService.isOrganizationExist(organizationId)

        then: "result should be true"
        result

        1 * organizationRepository.existsByIdAndStatus(organizationId, status) >> Boolean.TRUE
        0 * organizationRepository._
    }

    def "IsOrganizationExist should return false if organization with given organization id is not exist"() {
        given: "organization id"
        def organizationId = -1L
        def status = OrganizationStatus.ACTIVE

        when: "calls method with the id"
        def result = organizationService.isOrganizationExist(organizationId)

        then: "result should be false"
        !result

        1 * organizationRepository.existsByIdAndStatus(organizationId, status) >> Boolean.FALSE
        0 * organizationRepository._
    }
}
