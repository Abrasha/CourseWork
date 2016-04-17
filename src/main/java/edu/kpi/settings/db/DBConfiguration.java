/**
 * This class is optional, because Spring does so much magic ...
 */

//package edu.kpi.settings.db;
//
//import org.springframework.beans.factory.annotation.Autowire;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//@Configuration
//@PropertySource("classpath:application.properties")
//public class DBConfiguration {
//
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//
//    @Value("${spring.datasource.username}")
//    private String dbUsername;
//
//    @Value("${spring.datasource.password}")
//    private String dbPassword;
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String dbDriverName;
//
//    @Bean(autowire = Autowire.BY_TYPE)
//    public DataSource dataSource() {
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(dbUrl);
//        dataSource.setDriverClassName(dbDriverName);
//        dataSource.setPassword(dbPassword);
//        dataSource.setUsername(dbUsername);
//        return dataSource;
//    }
//
//}
