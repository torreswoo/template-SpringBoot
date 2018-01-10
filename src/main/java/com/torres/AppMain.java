package com.torres;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.config.EnableAdminServer;

import java.util.Arrays;

@SpringBootApplication
//@EnableAdminServer
@Slf4j
public class AppMain {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AppMain.class, args);

        log.info("### Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames)
            log.info(" - {}", beanName);
    }
}
