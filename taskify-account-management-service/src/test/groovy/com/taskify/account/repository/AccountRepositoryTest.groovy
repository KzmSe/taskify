package com.taskify.account.repository

import com.taskify.account.entity.enums.AccountStatus
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
class AccountRepositoryTest extends Specification {

    @Autowired
    private AccountRepository accountRepository;

    def "ExistsByIdAndStatus should return true if account with given id and status is exist"() {
        given: "id and status"
        def id = 1L
        def status = AccountStatus.ACTIVE

        when: "calls method with id and status"
        def result = accountRepository.existsByIdAndStatus(id, status)

        then: "result should be true"
        result
    }

    def "ExistsByIdAndStatus should return false if account with given id and status is not exist"() {
        given: "id and status"
        def id = -1L
        def status = AccountStatus.ACTIVE

        when: "calls method with id and status"
        def result = accountRepository.existsByIdAndStatus(id, status)

        then: "result should be false"
        !result
    }

    def "FindByEmailAndStatus should return optional account if account with given email and status is exist"() {
        given: "email and status"
        def email = "admin@outlook.com"
        def status = AccountStatus.ACTIVE

        when: "calls method with the email and status"
        def result = accountRepository.findByEmailAndStatus(email, status)

        then: "result should not be empty"
        result.isPresent()

        and: "email must be provided"
        result.get().email == email
    }

    def "FindByEmailAndStatus should return empty optional if account with given email and status is not exist"() {
        given: "email and status"
        def email = "notexist@outlook.com"
        def status = AccountStatus.ACTIVE

        when: "calls method with the email and status"
        def result = accountRepository.findByEmailAndStatus(email, status)

        then: "result should be empty"
        result.isEmpty()
    }
}
