package com.taskify.organization.controller;

import com.taskify.organization.controller.dto.organization.OrganizationCreationRequest;
import com.taskify.organization.controller.dto.organization.OrganizationResponse;
import com.taskify.organization.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "Create a new organization with default account")
    public ResponseEntity<OrganizationResponse> create(@Valid @RequestBody OrganizationCreationRequest request) {
        return ResponseEntity.ok(organizationService.create(request));
    }

    @GetMapping("/organizations/{organizationId}/existence")
    @ApiOperation(value = "Check organization with given id is exist")
    public ResponseEntity<Boolean> isOrganizationExist(@PathVariable(name = "organizationId") Long organizationId) {
        return ResponseEntity.ok(organizationService.isOrganizationExist(organizationId));
    }
}
