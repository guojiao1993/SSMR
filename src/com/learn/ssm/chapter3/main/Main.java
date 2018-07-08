package com.learn.ssm.chapter3.main;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.learn.ssm.chapter3.mapper.RoleMapperUsingAnnotation;
import com.learn.ssm.chapter3.mapper.RoleMapperUsingXML;
import com.learn.ssm.chapter3.pojo.Role;
import com.learn.ssm.chapter3.utils.SqlSessionFactoryUtils;
public class Main {

	public static void main(String[] args) {
//		testRoleMapperUsingXML();
		testRoleMapperUsingAnnotation();
	}
	
	private static void testRoleMapperUsingXML() {
		Logger log = Logger.getLogger(Main.class);
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSessionUsingXML();
			RoleMapperUsingXML roleMapper = sqlSession.getMapper(RoleMapperUsingXML.class);
			// 查询已有值
			Role existRole = roleMapper.getRole(1L);
			log.info(existRole.getRoleName());
			// 插入新增值
			Role newRole = new Role();
			newRole.setRoleName("wyp");
			newRole.setNote("test");
			roleMapper.insertRole(newRole);
			// 查询新增值
			Role insertedRole = roleMapper.getRole(2L);
			log.info(insertedRole.getRoleName());
			// 提交事务
			sqlSession.commit();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	private static void testRoleMapperUsingAnnotation() {
		Logger log = Logger.getLogger(Main.class);
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSessionUsingAnnotation();
			RoleMapperUsingAnnotation roleMapper = sqlSession.getMapper(RoleMapperUsingAnnotation.class);
			Role role = roleMapper.getRole(1L);
			log.info(role.getRoleName());
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
}