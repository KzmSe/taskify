package com.taskify.task.repository

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
class TaskRepositoryTest extends Specification {

    @Autowired
    private TaskRepository taskRepository;

    def "FindByOrganizationId should return list of tasks if tasks with given organization id are exist"() {
        given: "organization id"
        def organizationId = 1L

        when: "calls method with the id"
        def result = taskRepository.findByOrganizationId(organizationId)

        then: "result should not be empty"
        !result.empty
    }

    def "FindByOrganizationId should return empty list if tasks with given organization id are not exist"() {
        given: "organization id"
        def organizationId = -1L

        when: "calls method with the id"
        def result = taskRepository.findByOrganizationId(organizationId)

        then: "result should not be empty"
        result.empty
    }

    def "FindByAccountIdAndOrganizationId should return list of tasks if tasks with given account id and organization id are exist"() {
        given: "account id and organization id"
        def accountId = 1L
        def organizationId = 1L

        when: "calls method with the ids"
        def result = taskRepository.findByAccountIdAndOrganizationId(accountId, organizationId)

        then: "result should not be empty"
        !result.empty
    }

    def "FindByAccountIdAndOrganizationId should return empty  list if tasks with given account id and organization id are not exist"() {
        given: "account id and organization id"
        def accountId = -1L
        def organizationId = -1L

        when: "calls method with the ids"
        def result = taskRepository.findByAccountIdAndOrganizationId(accountId, organizationId)

        then: "result should be empty"
        result.empty
    }
}
