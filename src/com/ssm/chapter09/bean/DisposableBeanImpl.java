package com.ssm.chapter09.bean;

import org.springframework.beans.factory.DisposableBean;

public class DisposableBeanImpl implements DisposableBean {

	@Override
	public void destroy() throws Exception {
		System.out.println("���ýӿ�DisposableBean��destroy����");
	}

}
