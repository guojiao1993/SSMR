package com.learn.ssm.chapter3.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.learn.ssm.chapter3.mapper.RoleMapperUsingAnnotation;
import com.learn.ssm.chapter3.mapper.RoleMapperUsingXML;
import com.learn.ssm.chapter3.pojo.Role;

public class SqlSessionFactoryUtils {

	private final static Class<SqlSessionFactoryUtils> LOCK = SqlSessionFactoryUtils.class;

	private static SqlSessionFactory sqlSessionFactory = null;

	private SqlSessionFactoryUtils() {
	}

	/**
	 * 通过XML配置SqlSessionFactory
	 * @return
	 */
	public static SqlSessionFactory getSqlSessionFactoryUsingXML() {
		synchronized (LOCK) {
			if (sqlSessionFactory != null) {
				return sqlSessionFactory;
			}
			String resource = "com/learn/ssm/chapter3/config/mybatis-config.xml";
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			return sqlSessionFactory;
		}
	}
	
	
	/**
	 * 通过代码配置SqlSessionFactory
	 * @return
	 */
	public static SqlSessionFactory getSqlSessionFactoryUsingCode() {
		synchronized (LOCK) {
			if (sqlSessionFactory != null) {
				return sqlSessionFactory;
			}
			//数据库连接池信息
			PooledDataSource dataSource = new PooledDataSource();
			dataSource.setDriver("com.mysql.cj.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("root");
			dataSource.setUrl("jdbc:mysql://localhost:3306/chapter3?useSSL=false&serverTimezone=GMT%2B8");
			dataSource.setDefaultAutoCommit(false);
			//采用MyBatis的JDBC事务方式
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment("development", transactionFactory, dataSource);
			//创建Configuration对象
			Configuration configuration = new Configuration(environment);
			//注册一个MyBatis上下文别名
			configuration.getTypeAliasRegistry().registerAlias("role", Role.class);
			//加入一个映射器
			configuration.addMapper(RoleMapperUsingXML.class);
			configuration.addMapper(RoleMapperUsingAnnotation.class);
			//使用SqlSessionFactoryBuilder构建SqlSessionFactory
			sqlSessionFactory =  new SqlSessionFactoryBuilder().build(configuration);
			return sqlSessionFactory; 	
		}
	}

	public static SqlSession openSqlSessionUsingXML() {
		if (sqlSessionFactory == null) {
			getSqlSessionFactoryUsingXML();
		}
		return sqlSessionFactory.openSession();
	}

	public static SqlSession openSqlSessionUsingAnnotation() {
		if (sqlSessionFactory == null) {
			getSqlSessionFactoryUsingCode();
		}
		return sqlSessionFactory.openSession();
	}
	
}