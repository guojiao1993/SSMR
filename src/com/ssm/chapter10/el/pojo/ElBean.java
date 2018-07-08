package com.ssm.chapter10.el.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("elBean")
public class ElBean {
	
    //ͨ��beanName��ȡbean��Ȼ��ע��
	@Value("#{role}")
	private Role role;
	
    //��ȡbean������id
	@Value("#{role.id}")
	private Long id;
	
    //����bean��getNote��������ȡ��ɫ����
	@Value("#{role.getNote().toString()}")
	private String note;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	} 
	
	
}
