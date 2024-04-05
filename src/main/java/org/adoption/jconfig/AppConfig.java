package org.adoption.jconfig;

import org.adoption.dao.AdopterDAO;
import org.adoption.dao.FactoryDAO;
import org.adoption.dao.InMemoryDAO;
import org.adoption.services.AdopterService;
import org.adoption.services.AdopterServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
//    @Bean
//    public AdopterDAO adopterDAO(){
//        return FactoryDAO.adopterDAO();
//    }
//
//    @Bean
//    public AdopterService adopterService(){
//        return new AdopterServiceImpl();
//    }
}
