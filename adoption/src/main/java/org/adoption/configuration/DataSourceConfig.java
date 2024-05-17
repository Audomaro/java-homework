package org.adoption.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        String url = "jdbc:postgresql://localhost:5433/adoptapp";
        String user = "larku";
        String pw = System.getenv("larku");
        return new DriverManagerDataSource(url, user, pw);
    }
}
