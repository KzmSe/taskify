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
class AccountCollectionRepositoryTest extends Specification {

    @Autowired
    private AccountCollectionRepository accountCollectionRepository;

    def "ExistsByTask_IdAndAccountId should return true if taskAccountCollection with given task id and account id is exist"() {
        given: "task id and account id"
        def taskId = 1L
        def accountId = 1L

        when: "calls method with the ids"
        def result = accountCollectionRepository.existsByTask_IdAndAccountId(taskId, accountId)

        then: "result should be true"
        result
    }

    def "ExistsByTask_IdAndAccountId should return false if taskAccountCollection with given task id and account id is not exist"() {
        given: "task id and account id"
        def taskId = -1L
        def accountId = -1L

        when: "calls method with the ids"
        def result = accountCollectionRepository.existsByTask_IdAndAccountId(taskId, accountId)

        then: "result should be false"
        !result
    }
}
