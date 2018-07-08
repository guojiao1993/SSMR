package com.ssm.chapter09.main;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.chapter09.pojo.JuiceMaker;
import com.ssm.chapter09.pojo.JuiceMaker2;

public class Main {
	public static void main(String[] args) {
		testCommon();
		testIoC();
	}
	
	public static void testCommon() {
		Logger log = Logger.getLogger(Main.class);
		JuiceMaker juiceMaker = new JuiceMaker();
		juiceMaker.setWater("¿óÈªË®");
		juiceMaker.setFruit("³È×Ó");
		juiceMaker.setSugar("ÉÙÌÇ");
		log.info(juiceMaker.makeJuice());
	}
	
	public static void testIoC() {
		Logger log = Logger.getLogger(Main.class);
		ClassPathXmlApplicationContext ctx = 
				new ClassPathXmlApplicationContext("com/ssm/chapter09/config/spring-cfg.xml");
		JuiceMaker2 juiceMaker2 = (JuiceMaker2) ctx.getBean("juiceMaker2");
		log.info(juiceMaker2.makeJuice());
		ctx.close();
	}
}
