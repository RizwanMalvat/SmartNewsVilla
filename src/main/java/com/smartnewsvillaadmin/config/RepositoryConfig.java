package com.smartnewsvillaadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.smartnewsvillaadmin.repositories")
public class RepositoryConfig {

}