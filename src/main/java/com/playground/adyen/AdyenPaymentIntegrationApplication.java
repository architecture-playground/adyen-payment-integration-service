package com.playground.adyen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories
//@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class AdyenPaymentIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdyenPaymentIntegrationApplication.class, args);
    }
}
