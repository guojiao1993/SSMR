package com.ssm.chapter03.mapper;

import org.apache.ibatis.annotations.Select;

import com.ssm.chapter03.pojo.Role;

public interface RoleMapperUsingAnnotation {
	
	@Select("select id, role_name as roleName, note from t_role where id=#{id}")
	public Role getRole(Long id);
	
}
