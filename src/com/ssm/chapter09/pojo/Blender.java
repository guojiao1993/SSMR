package com.ssm.chapter09.pojo;

public class Blender {

	/**
	 * 搅拌
	 * 
	 * @param water
	 *            水描述
	 * @param fruit
	 *            水果描述
	 * @param sugar
	 *            糖描述
	 * @return 果汁
	 */
	public String mix(String water, String fruit, String sugar) {
		String juice = "这是一杯由[液体：" + water + ", 水果：" + fruit + ", 糖量：" + sugar + "]组成的果汁";
		return juice;
	}
}
