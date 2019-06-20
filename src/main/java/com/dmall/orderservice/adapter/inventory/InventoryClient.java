package com.dmall.orderservice.adapter.inventory;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//TODO: add a fallback for InventoryClient

@EnableCircuitBreaker
@EnableDiscoveryClient
@FeignClient(value = "inventory-service",fallback = InventoryClient.class)
public interface InventoryClient {
    @RequestMapping(method = RequestMethod.POST, value = "/inventories/lock")
    String lock(Lock lock);

    @RequestMapping(method = RequestMethod.PUT, value = "/inventories/lock/{lockId}")
    void unlock(@PathVariable("lockId") String lockId);
}
