package com.ssm.chapter17.jedis;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {

	public void testJedis() {
		Logger log = Logger.getLogger(JedisTest.class);
		// 连接Redis
		 Jedis jedis = new Jedis("localhost", 6379);
		// 从连接池中获取单个连接
//		Jedis jedis = testPool().getResource();
		// 如果需密码
		// jedis.auth("password");
		// 记录操作次数
		int i = 0;
		try {
			// 开始毫秒数
			long start = System.currentTimeMillis();
			while (true) {
				long end = System.currentTimeMillis();
				// 当大于等于1000毫秒（相当于1秒）时，结束操作
				if (end - start >= 1000) {
					break;
				}
				i++;
				jedis.set("test" + i, i + "");
			}
		} finally {
			// 关闭连接
			jedis.close();
		}
		// 打印1秒内对Redis的操作次数
		log.info("redis每秒操作：" + i + "次");
	}

	private JedisPool testPool() {
		JedisPoolConfig poolCfg = new JedisPoolConfig();
		// 最大空闲数
		poolCfg.setMaxIdle(50);
		// 最大连接数
		poolCfg.setMaxTotal(100);
		// 最大等待毫秒数
		poolCfg.setMaxWaitMillis(20000);
		// 使用配置创建连接池
		JedisPool pool = new JedisPool(poolCfg, "localhost");
		return pool;
	}

}
