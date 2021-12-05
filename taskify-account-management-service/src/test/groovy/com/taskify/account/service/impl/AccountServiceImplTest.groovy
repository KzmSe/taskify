package com.taskify.account.service.impl

import com.taskify.account.entity.Account
import com.taskify.account.entity.AccountOrganizationCollection
import com.taskify.account.entity.enums.AccountStatus
import com.taskify.account.exception.DataNotFoundException
import com.taskify.account.repository.AccountRepository
import com.taskify.account.repository.OrganizationCollectionRepository
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import spock.lang.Specification

class AccountServiceImplTest extends Specification {

    def accountRepository
    def passwordEncoder
    def organizationCollectionRepository
    def defaultPassword
    def enhancedRandom
    def accountService

    void setup() {
        accountRepository = Mock(AccountRepository) as AccountRepository
        passwordEncoder = Mock(BCryptPasswordEncoder) as BCryptPasswordEncoder
        organizationCollectionRepository = Mock(OrganizationCollectionRepository) as OrganizationCollectionRepository
        defaultPassword = 'defaultPassword'
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandom()
        accountService = new AccountServiceImpl(accountRepository, passwordEncoder, organizationCollectionRepository)
    }

    def "CreateDefault should return AccountResponse if saves account successfully"() {

    }

    def "Create should return AccountResponse if saves account successfully"() {

    }

    def "Assign should return true if assigns account to organization"() {
        given: "organizationId and accountId"
        def organizationId = 1L
        def accountId = 1L
        def collection = enhancedRandom.nextObject(AccountOrganizationCollection)

        when: "calls method with the ids"
        def result = accountService.assign(organizationId, accountId)

        then: "result should be true"
        result

        1 * accountRepository.existsByIdAndStatus(accountId, AccountStatus.ACTIVE) >> Boolean.TRUE
        1 * organizationCollectionRepository.save(_) >> collection
        0 * accountRepository._
        0 * organizationCollectionRepository._
    }

    def "Assign should throw DataNotFoundException if account not found with given account id and status"() {
        given: "organizationId and accountId"
        def organizationId = 1L
        def accountId = -1L

        when: "calls method with the ids"
        accountService.assign(organizationId, accountId)

        then: "exception should be thrown and contain this message"
        def ex = thrown(DataNotFoundException)
        ex.message == "Account not found with given id"

        1 * accountRepository.existsByIdAndStatus(accountId, AccountStatus.ACTIVE) >> Boolean.FALSE
        0 * _._
    }

    def "IsAccountExist should return true if account with given id is exist"() {
        given: "accountId"
        def accountId = 1L

        when: "calls method with the ids"
        def result = accountService.isAccountExist(accountId)

        then: "result should be true"
        result

        1 * accountRepository.existsByIdAndStatus(accountId, AccountStatus.ACTIVE) >> Boolean.TRUE
        0 * _._
    }

    def "IsAccountExist should return false if account with given id is not exist"() {
        given: "accountId"
        def accountId = -1L

        when: "calls method with the ids"
        def result = accountService.isAccountExist(accountId)

        then: "result should be true"
        !result

        1 * accountRepository.existsByIdAndStatus(accountId, AccountStatus.ACTIVE) >> Boolean.FALSE
        0 * _._
    }

    def "LoadUserByUsername should return UserDetails if account with given id and status is exist"() {
        given: "email"
        def email = 'admin@outlook.com'
        def account = enhancedRandom.nextObject(Account)

        when: "calls method with the email"
        def result = accountService.loadUserByUsername(email)

        then: "result should be true"
        result != null
        result.username != null


        1 * accountRepository.findByEmailAndStatus(email, AccountStatus.ACTIVE) >> Optional.of(account)
        0 * _._
    }

    def "LoadUserByUsername should throw DataNotFoundException if account with given id and status is not exist"() {
        given: "email"
        def email = 'notexist@outlook.com'

        when: "calls method with the email"
        accountService.loadUserByUsername(email)

        then: "exception should be thrown and contain this message"
        def ex = thrown(DataNotFoundException)
        ex.message == "Account not found with given email"


        1 * accountRepository.findByEmailAndStatus(email, AccountStatus.ACTIVE) >> Optional.empty()
        0 * _._
    }
}
