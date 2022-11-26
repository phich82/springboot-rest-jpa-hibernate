package com.maison.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/courses").setViewName("course");
        registry.addViewController("/about").setViewName("about");
    }

    // @Bean
    // public DataSource myDataSource() {
    //     DriverManagerDataSource dataSource = new DriverManagerDataSource();
    //     dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    //     dataSource.setUrl("jdbc:mysql://localhost:3306/eazyschool");
    //     dataSource.setUsername("user");
    //     dataSource.setPassword("password");
    //     return dataSource;
    // }
}
