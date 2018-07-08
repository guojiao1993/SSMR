package com.ssm.chapter17.main;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import com.ssm.chapter17.jedis.JedisTest;
import com.ssm.chapter17.pojo.Role;

public class Main {

	public static void main(String[] args) {
		new JedisTest().testJedis();
		testSpring();
		testSessionCallback();
	}

	private static void testSpring() {
		Logger log = Logger.getLogger(Main.class);
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/ssm/chapter17/config/applicationContext.xml");
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		Role role = new Role();
		role.setId(1L);
		role.setRoleName("role_name_1");
		role.setNote("note_1");
		redisTemplate.opsForValue().set("role_1", role);
		Role role1 = (Role) redisTemplate.opsForValue().get("role_1");
		log.info(role1.getRoleName());
	}

	private static void testSessionCallback() {
		Logger log = Logger.getLogger(Main.class);
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/ssm/chapter17/config/applicationContext.xml");
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		Role role = new Role();
		role.setId(1);
		role.setRoleName("role_name_1");
		role.setNote("role_note_1");
		SessionCallback callBack = new SessionCallback<Role>() {
			@Override
			public Role execute(RedisOperations ops) throws DataAccessException {
				ops.boundValueOps("role_1").set(role);
				return (Role) ops.boundValueOps("role_1").get();
			}
		};
		Role savedRole = (Role) redisTemplate.execute(callBack);
		log.info(savedRole.getId());
	}

}
