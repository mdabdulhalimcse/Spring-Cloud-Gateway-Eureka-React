package com.abdulhalim;

import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@EnableHystrix
public class FallbackController {

    @RequestMapping("/employeeFallback")
    public Mono<String> orderServiceFallBack() {
        return Mono.just("Employee Service is taking too long to respond or is down. Please try again later");
    }
    @RequestMapping("/departmentFallback")
    public Mono<String> paymentServiceFallBack() {
        return Mono.just("Department Service is taking too long to respond or is down. Please try again later");
    }
}