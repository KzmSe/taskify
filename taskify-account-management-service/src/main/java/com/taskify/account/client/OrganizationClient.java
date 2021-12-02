package com.taskify.account.client;

import com.taskify.account.client.dto.OrganizationResponse;
import com.taskify.account.controller.dto.organization.OrganizationCreationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${openfeign.clients.organization.name}", url = "${openfeign.clients.organization.url}", configuration = FeignConfig.class)
public interface OrganizationClient {

    @RequestMapping(method = RequestMethod.POST, value = "/worganizationms/organizations")
    ResponseEntity<OrganizationResponse> create(@RequestBody OrganizationCreationRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/worganizationms/organizations/{organizationId}/accounts/{accountId}")
    ResponseEntity<Boolean> assign(@PathVariable(name = "organizationId") Long organizationId,
                                   @PathVariable(name = "accountId") Long accountId);
}
