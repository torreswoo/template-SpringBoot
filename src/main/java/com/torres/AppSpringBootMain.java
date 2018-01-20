package com.torres;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class AppSpringBootMain {

    @Bean(destroyMethod = "stop")
    @Autowired
    public AppSpringBootInterface appSpringBootInterface(AppSpringBootInterface appSpringBootInterface) {
        return appSpringBootInterface;
    }

    // AppSpringBootMain - main()
    public static void main(String[] args) {
        // Application Context
        ApplicationContext ctx = SpringApplication.run(AppSpringBootMain.class, args);
        log.info("### Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames)
            log.info(" - {}", beanName);

        // AppMain service start
        AppSpringBootInterface appMain = ctx.getBean("appSpringBootInterface", AppSpringBootInterface.class);
        appMain.start();
    }
}
