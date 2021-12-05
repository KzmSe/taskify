package com.taskify.task.service.impl

import com.taskify.task.client.AccountClient
import com.taskify.task.controller.dto.task.TaskResponse
import com.taskify.task.entity.Task
import com.taskify.task.entity.TaskAccountCollection
import com.taskify.task.exception.DataNotFoundException
import com.taskify.task.repository.AccountCollectionRepository
import com.taskify.task.repository.TaskRepository
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.springframework.dao.DataIntegrityViolationException
import spock.lang.Specification

class TaskServiceImplTest extends Specification {

    def taskRepository
    def accountCollectionRepository
    def accountClient
    def enhancedRandom
    def taskService

    void setup() {
        taskRepository = Mock(TaskRepository) as TaskRepository
        accountCollectionRepository = Mock(AccountCollectionRepository) as AccountCollectionRepository
        accountClient = Mock(AccountClient) as AccountClient
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandom()
        taskService = new TaskServiceImpl(taskRepository, accountCollectionRepository, accountClient)
    }

    def "Create"() {
    }

    def "Assign should throw DataIntegrityViolationException if task is already assigned to the account"() {
        given: "taskId and accountId"
        def taskId = 1L
        def accountId = 1L
        def authHeader = "Bearer jwttoken"

        when: "calls method with the ids"
        taskService.assign(authHeader, taskId, accountId)

        then: "exception should be thrown and contain this message"
        def ex = thrown(DataIntegrityViolationException)
        ex.message == "Task is already assigned to the account"

        1 * accountCollectionRepository.existsByTask_IdAndAccountId(taskId, accountId) >> Boolean.TRUE
        0 * _._
    }

    def "Assign should throw DataNotFoundException if account with given id is not exist"() {
        given: "taskId and accountId"
        def taskId = 1L
        def accountId = 1L
        def authHeader = "Bearer jwttoken"

        when: "calls method with the ids"
        taskService.assign(authHeader, taskId, accountId)

        then: "exception should be thrown and contain this message"
        def ex = thrown(DataNotFoundException)
        ex.message == "Account not found with given id"

        1 * accountCollectionRepository.existsByTask_IdAndAccountId(taskId, accountId) >> Boolean.FALSE
        1 * accountClient.isAccountExist(authHeader, accountId) >> Boolean.TRUE
        0 * _._
    }

    def "Assign should return true if assigns task to the account successfully"() {
        given: "taskId and accountId"
        def taskId = 1L
        def accountId = 1L
        def authHeader = "Bearer jwttoken"
        def collection = enhancedRandom.nextObject(TaskAccountCollection)

        when: "calls method with the ids"
        def result = taskService.assign(authHeader, taskId, accountId)

        then: "result should be true"
        result

        1 * accountCollectionRepository.existsByTask_IdAndAccountId(taskId, accountId) >> Boolean.FALSE
        1 * accountClient.isAccountExist(authHeader, accountId) >> Boolean.FALSE
        1 * accountCollectionRepository.save(_) >> collection
        0 * _._
    }

    def "FindAllByOrganization should return list of TaskResponse if tasks with given organization id are exist"() {
        given: "organizationId"
        def organizationId = 1L
        def tasks = enhancedRandom.randomListOf(10, Task.class)

        when: "calls method with organizationId"
        def result = taskService.findAllByOrganization(organizationId)

        then: "result shouldn't be null"
        result != null
        result.size() >= 1

        1 * taskRepository.findByOrganizationId(organizationId) >> tasks
        0 * _._
    }

    def "FindAllByOrganization should return empty list if tasks with given organization id are not exist"() {
        given: "organizationId"
        def organizationId = -1L

        when: "calls method with organizationId"
        def result = taskService.findAllByOrganization(organizationId)

        then: "result shouldn be empty list"
        result != null
        result.size() == 0

        1 * taskRepository.findByOrganizationId(organizationId) >> []
        0 * _._
    }

    def "FindAllByAccount should return list of TaskResponse if tasks with given account id and organization id are exist"() {
        given: "organizationId and accountId"
        def organizationId = 1L
        def accountId = 1L
        def tasks = enhancedRandom.randomListOf(10, Task.class)

        when: "calls method with organizationId and accountId"
        def result = taskService.findAllByAccount(organizationId, accountId)

        then: "result shouldn't be null"
        result != null
        result.size() >= 1

        1 * taskRepository.findByAccountIdAndOrganizationId(accountId, organizationId) >> tasks
        0 * _._
    }

    def "FindAllByAccount should return empty list if tasks with given account id and organization id are not exist"() {
        given: "organizationId and accountId"
        def organizationId = -1L
        def accountId = -1L

        when: "calls method with organizationId and accountId"
        def result = taskService.findAllByAccount(organizationId, accountId)

        then: "result shouldn be empty list"
        result != null
        result.size() == 0

        1 * taskRepository.findByAccountIdAndOrganizationId(accountId, organizationId) >> []
        0 * _._
    }
}
