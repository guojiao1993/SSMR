package com.ssm.chapter09.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessorImpl implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("��" + bean.getClass().getSimpleName() + "������" + beanName + "��ʼʵ����");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("��" + bean.getClass().getSimpleName() + "������" + beanName + "ʵ�������");
		return bean;
	}

}