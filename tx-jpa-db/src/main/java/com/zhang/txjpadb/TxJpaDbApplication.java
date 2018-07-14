package com.zhang.txjpadb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//exclude = DataSourceAutoConfiguration.class
@SpringBootApplication
public class TxJpaDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TxJpaDbApplication.class, args);
	}
}
