package com.smartnewsvillaadmin.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

//    @Bean
//    @Bean(destroyMethod = "close")
//    public DataSource dataSource() {
//        JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//        dsLookup.setResourceRef(true);
//        //DataSource dataSource = dsLookup.getDataSource(this.env.getProperty("database.jndi"));
////        DataSource dataSource = dsLookup.getDataSource("java:/MuddyV2Beta");
//        DataSource dataSource = dsLookup.getDataSource("java:/esellv2Datasourse");
//        return dataSource;
//    }
    // local
//    @Bean(destroyMethod = "close")
//    public DataSource dataSource() {
//        JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//        dsLookup.setResourceRef(true);
//        //DataSource dataSource = dsLookup.getDataSource(this.env.getProperty("database.jndi"));
////        DataSource dataSource = dsLookup.getDataSource("java:/MuddyV2Beta");
//            DataSource dataSource = dsLookup.getDataSource("java:/eSellDatasourse");
//        return dataSource;
//    }
    //Tomcat
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/smartnewsvilla");
//        dataSource.setUrl("jdbc:postgresql://ec2-54-83-26-65.compute-1.amazonaws.com:5432/d6ta0l2l3b749l");
//        dataSource.setUrl("jdbc:postgresql://192.168.1.4:5432/DemoDB");
//        dataSource.setUsername("qxovvtznqlqmkr");
//        dataSource.setPassword("072a9b85a9bd4dc2fc7524f08ea8e35677208385ea908a09dc4ca75e7fd1254c");
        dataSource.setUsername("postgres");
        dataSource.setPassword("Shehnaz@786");
//        dataSource.setPassword("mama");
        dataSource.setMaxActive(100);
        dataSource.setMaxIdle(30);
        dataSource.setMaxWait(2000);
        return dataSource;
    }
}
