package com.esprit.ms.microservicemesssagerie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
@EnableFeignClients
public class MicroserviceMesssagerieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceMesssagerieApplication.class, args);
    }

}
