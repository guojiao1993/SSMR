package com.ssm.chapter09.pojo;

public class Blender {

	/**
	 * ����
	 * 
	 * @param water
	 *            ˮ����
	 * @param fruit
	 *            ˮ������
	 * @param sugar
	 *            ������
	 * @return ��֭
	 */
	public String mix(String water, String fruit, String sugar) {
		String juice = "����һ����[Һ�壺" + water + ", ˮ����" + fruit + ", ������" + sugar + "]��ɵĹ�֭";
		return juice;
	}
}
