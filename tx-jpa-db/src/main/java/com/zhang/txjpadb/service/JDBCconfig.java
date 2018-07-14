package com.zhang.txjpadb.service;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JDBCconfig {

    @Bean
    @Primary
    @ConfigurationProperties( prefix = "spring.ds.user")
    public DataSourceProperties userDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource userDataSource(){
        return userDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean
    public JdbcTemplate userJdbcTemplate(@Qualifier("userDataSource") DataSource userDataSource){
        return new JdbcTemplate( userDataSource );
    }

//    @Bean
//   public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
//        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        adapter.setGenerateDdl( false );
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter( adapter );
//        factory.setDataSource( userDataSource() );
//        factory.setPackagesToScan( "com.zhang.txjpadb");
//        return factory;
//   }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(){
//        JpaTransactionManager userTM = new JpaTransactionManager(  );
//        userTM.setEntityManagerFactory( entityManagerFactory().getObject() );
//        DataSourceTransactionManager orderTM = new DataSourceTransactionManager( orderDataSource() );
//        ChainedTransactionManager tm = new ChainedTransactionManager( userTM , orderTM );
//        return tm;
//    }

    @Bean
    @ConfigurationProperties( prefix = "spring.order.user")
    public DataSourceProperties orderDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource orderDataSource(){
        return orderDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean
    public JdbcTemplate orderJdbcTemplate(@Qualifier("orderDataSource") DataSource orderDataSource){
        return new JdbcTemplate( orderDataSource );
    }
}
