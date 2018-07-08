package com.ssm.chapter10.annotation.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = { "com.ssm.chapter10.annotation.*" }, excludeFilters = {
		@Filter(type = FilterType.REGEX, pattern = "com.ssm.chapter10.annotation.config.ApplicationConfig") })
@PropertySource(value = { "classpath:com/ssm/chapter10/config/database-config.properties" }, ignoreResourceNotFound = true)
public class AutowiredConfig {

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		Properties props = new Properties();
		props.setProperty("driver", "com.mysql.cj.jdbc.Driver");
		props.setProperty("url", "jdbc:mysql://localhost:3306/chapter10?serverTimezone=GMT%2B8");
		props.setProperty("username", "root");
		props.setProperty("password", "root");
		DataSource dataSource = null;
		try {
			dataSource = BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	
}
