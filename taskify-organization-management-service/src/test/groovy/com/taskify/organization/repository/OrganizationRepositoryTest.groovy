package com.taskify.organization.repository

import com.taskify.organization.entity.enums.OrganizationStatus
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import spock.lang.Specification

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ImportAutoConfiguration([RibbonAutoConfiguration.class, FeignRibbonClientAutoConfiguration.class, FeignAutoConfiguration.class])
class OrganizationRepositoryTest extends Specification {

    @Autowired
    private OrganizationRepository organizationRepository;

    def "ExistsByIdAndStatus should return true if organization with given id and status is exist"() {
        given: "id and status"
        def id = 1L
        def status = OrganizationStatus.ACTIVE

        when: "calls method with id and status"
        def result = organizationRepository.existsByIdAndStatus(id, status)

        then: "result should be true"
        result
    }

    def "ExistsByIdAndStatus should return false if organization with given id and status is not exist"() {
        given: "id and status"
        def id = -1L
        def status = OrganizationStatus.ACTIVE

        when: "calls method with id and status"
        def result = organizationRepository.existsByIdAndStatus(id, status)

        then: "result should be true"
        !result
    }
}
