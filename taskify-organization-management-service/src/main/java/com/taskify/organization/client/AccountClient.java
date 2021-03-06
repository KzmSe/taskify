package com.taskify.organization.client;

import com.taskify.organization.client.dto.AccountResponse;
import com.taskify.organization.client.dto.RegistrationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${openfeign.clients.account.name}", url = "${openfeign.clients.account.url}", configuration = FeignConfig.class)
public interface AccountClient {

    @RequestMapping(method = RequestMethod.POST, value = "/waccountms/accounts/default")
    AccountResponse createDefault(@RequestBody RegistrationRequest request);
}
