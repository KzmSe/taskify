package com.taskify.task.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${openfeign.clients.account.name}", url = "${openfeign.clients.account.url}", configuration = FeignConfig.class)
public interface AccountClient {

    @RequestMapping(method = RequestMethod.GET, value = "/waccountms/accounts/{accountId}/existence")
    ResponseEntity<Boolean> isAccountExist(@PathVariable(name = "accountId") Long accountId);
}
