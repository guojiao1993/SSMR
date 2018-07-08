package com.ssm.chapter17.jedis;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {

	public void testJedis() {
		Logger log = Logger.getLogger(JedisTest.class);
		// ����Redis
		 Jedis jedis = new Jedis("localhost", 6379);
		// �����ӳ��л�ȡ��������
//		Jedis jedis = testPool().getResource();
		// ���������
		// jedis.auth("password");
		// ��¼��������
		int i = 0;
		try {
			// ��ʼ������
			long start = System.currentTimeMillis();
			while (true) {
				long end = System.currentTimeMillis();
				// �����ڵ���1000���루�൱��1�룩ʱ����������
				if (end - start >= 1000) {
					break;
				}
				i++;
				jedis.set("test" + i, i + "");
			}
		} finally {
			// �ر�����
			jedis.close();
		}
		// ��ӡ1���ڶ�Redis�Ĳ�������
		log.info("redisÿ�������" + i + "��");
	}

	private JedisPool testPool() {
		JedisPoolConfig poolCfg = new JedisPoolConfig();
		// ��������
		poolCfg.setMaxIdle(50);
		// ���������
		poolCfg.setMaxTotal(100);
		// ���ȴ�������
		poolCfg.setMaxWaitMillis(20000);
		// ʹ�����ô������ӳ�
		JedisPool pool = new JedisPool(poolCfg, "localhost");
		return pool;
	}

}
