package com.ed.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "fraud",
        url = "http://localhost:8081"
)
public interface FraudClient {
    @GetMapping(path = "api/v1/fraud-check/{email}")
    FraudCheckResponse isFraudster(@PathVariable("email") String email);
}
