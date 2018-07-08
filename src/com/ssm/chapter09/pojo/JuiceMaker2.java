package com.ssm.chapter09.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class JuiceMaker2 implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {
	private String beverageShop = null;
	private Source source = null;

	public String getBeverageShop() {
		return beverageShop;
	}

	public void setBeverageShop(String beverageShop) {
		this.beverageShop = beverageShop;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String makeJuice() {
		String juice = "����һ����" + beverageShop + "��Ʒ���ṩ��" + source.getSize() + source.getSugar() + source.getFruit();
		return juice;
	}

	public void init() {
		System.out.println("��" + this.getClass().getSimpleName() + "��ִ���Զ����ʼ������");
	}

	public void destroy() {
		System.out.println("��" + this.getClass().getSimpleName() + "��ִ���Զ������ٷ���");
	}

	@Override
	public void setBeanName(String arg0) {
		System.out.println("��" + this.getClass().getSimpleName() + "������BeanNameAware�ӿڵ�setBeanName����");

	}

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		System.out.println("��" + this.getClass().getSimpleName() + "������BeanFactoryAware�ӿڵ�setBeanFactory����");
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		System.out.println(
				"��" + this.getClass().getSimpleName() + "������ApplicationContextAware�ӿڵ�setApplicationContext����");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("��" + this.getClass().getSimpleName() + "������InitializingBean�ӿڵ�afterPropertiesSet����");
	}
}
