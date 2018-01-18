package com.torres.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.h2.server.web.WebServlet;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan({"com.torres.entity"})
@EnableJpaRepositories(basePackages = {"com.torres.repository"})
public class DatabaseConfig {

    // DataSource Config
    // - application.yml : spring.datasource:
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return (DataSource) DataSourceBuilder
            .create()
            .type(DataSource.class)  // DBCP - org.apache.tomcat.jdbc.pool.DataSource
            .build();
    }

    // H2 Config (Only for URL mapping)
    // - usage url mapping : /console
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

    // JPA
    // - usage : public interface ***Repository extends JpaRepository { ... }
//    @Bean
//    @ConfigurationProperties("spring.jpa")
//    public JpaProperties jpaProperites (){
//        return new JpaProperties();
//    }
}