package com.facespedes.finanzas.context.apibank.infrastructure.nordigen;

import com.facespedes.finanzas.context.apibank.infrastructure.BankFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@FeignClient(
        name = "apibank",
        url = "https://ob.nordigen.com/api/v2",
        configuration = BankFeignClientConfiguration.class
)
public interface BankApiClientNordigen {
    @RequestMapping("accounts/{account}/transactions/")
    Map<Object, Object> getTransactions(@PathVariable("account") String account);

    @PostMapping("token/new/")
    Map<String, String> authenticate(@RequestBody BankApiClientNordigenCredentials credentials);
}
