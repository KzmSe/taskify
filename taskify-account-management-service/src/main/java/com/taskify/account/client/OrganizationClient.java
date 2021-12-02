package com.taskify.account.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${openfeign.clients.organization.name}", url = "${openfeign.clients.organization.url}", configuration = FeignConfig.class)
public interface OrganizationClient {

    @RequestMapping(method = RequestMethod.GET, value = "/worganizationms/organizations/{organizationId}/existence")
    ResponseEntity<Boolean> existsByIdAndStatus(@PathVariable(name = "organizationId") Long organizationId);
}
