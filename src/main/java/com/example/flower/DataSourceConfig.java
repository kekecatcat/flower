package com.example.flower;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {
	@Bean
	@ConfigurationProperties(prefix="com.udacity.datasource")
	public DataSource getDataSource() {
		DataSourceBuilder dsb = DataSourceBuilder.create();
		//dsb.username("root");
		//dsb.password(SecurePasswordService());
		dsb.url("jdbc:mysql://localhost:3306/exampledb?serverTimezone=UTC");
		return dsb.build();
	}

	/*private String SecurePasswordService() {
		return "jlu8ncsu";
	}*/
}
