package com.ssm.chapter10.annotation.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.ssm.chapter10.annotation.condition.DataSourceCondition;
import com.ssm.chapter10.annotation.pojo.Role;
import com.ssm.chapter10.annotation.service.impl.RoleServiceImpl;
import com.ssm.chapter09.pojo.JuiceMaker2;
import com.ssm.chapter09.pojo.Source;

@ComponentScan(basePackageClasses = { Role.class, RoleServiceImpl.class }, excludeFilters = {
		@Filter(type = FilterType.REGEX, pattern = "com.ssm.chapter10.annotation.config.AutowiredConfig") })
// @ComponentScan(basePackages = {"com.ssm.chapter10.annotation.pojo",
// "com.ssm.chapter10.annotation.service"})
// @ComponentScan(basePackages = {"com.ssm.chapter10.annotation.pojo",
// "com.ssm.chapter10.annotation.service"},
// basePackageClasses = {Role.class, RoleServiceImpl.class})

// ����test8��ʱ�����������XML��ͬʱע���������ݿ����ӳصķ���
// @ImportResource({"classpath:com/ssm/chapter10/config/spring-dataSource.xml"})
@PropertySource(value = {
		"classpath:com/ssm/chapter10/config/database-config.properties" }, ignoreResourceNotFound = true)
public class ApplicationConfig {

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/***
	 * ����test8��ʱ����Ҫע�͵�����datasource����������쳣
	 */
	// @Bean(name = "dataSource")
	// public DataSource getDataSource() {
	// Properties props = new Properties();
	// props.setProperty("driver", "com.mysql.cj.jdbc.Driver");
	// props.setProperty("url",
	// "jdbc:mysql://localhost:3306/chapter10?serverTimezone=GMT%2B8");
	// props.setProperty("username", "root");
	// props.setProperty("password", "root");
	// DataSource dataSource = null;
	// try {
	// dataSource = BasicDataSourceFactory.createDataSource(props);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return dataSource;
	// }

	@Bean(name = "dataSource")
	@Conditional({ DataSourceCondition.class })
	public DataSource getDataSource(@Value("${jdbc.database.driver}") String driver,
			@Value("${jdbc.database.url}") String url, @Value("${jdbc.database.username}") String username,
			@Value("${jdbc.database.password}") String password) {
		Properties props = new Properties();
		props.setProperty("driver", driver);
		props.setProperty("url", url);
		props.setProperty("username", username);
		props.setProperty("password", password);
		DataSource dataSource = null;
		try {
			dataSource = BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	@Bean(name = "juiceMaker2", initMethod = "init", destroyMethod = "destroy")
	public JuiceMaker2 initJuiceMaker2() {
		JuiceMaker2 juiceMaker2 = new JuiceMaker2();
		juiceMaker2.setBeverageShop("����");
		Source source = new Source();
		source.setFruit("����");
		source.setSize("��");
		source.setSugar("����");
		juiceMaker2.setSource(source);
		return juiceMaker2;
	}

}