package com.taskify.organization.controller;

import com.taskify.organization.controller.dto.OrganizationCreationRequest;
import com.taskify.organization.controller.dto.OrganizationResponse;
import com.taskify.organization.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "Operations related to organization in Taskify Organization Management Service")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/organizations")
    @ApiOperation(value = "Create a new organization")
    public ResponseEntity<OrganizationResponse> create(@Valid @RequestBody OrganizationCreationRequest request) {
        return ResponseEntity.ok(organizationService.create(request));
    }

    @PostMapping("/organizations/{organizationId}/accounts/{accountId}")
    @ApiOperation(value = "Assign account to organization")
    public ResponseEntity<Boolean> assign(@PathVariable(name = "organizationId") Long organizationId,
                                          @PathVariable(name = "accountId") Long accountId) {
        return ResponseEntity.ok(organizationService.assign(organizationId, accountId));
    }
}
