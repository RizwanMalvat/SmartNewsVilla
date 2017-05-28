package com.smartnewsvillaadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
//, SecurityConfig.class
@Configuration
@Import({DataSourceConfig.class, JpaConfig.class, RepositoryConfig.class, AsyncConfig.class})
public class Config {

}
