package com.learn.ssm.chapter3.mapper;
import java.util.List;
import com.learn.ssm.chapter3.pojo.Role;
public interface RoleMapperUsingXML {
	public Role getRole(Long id);
	public int insertRole(Role role);
	public int deleteRole(Long id);
	public int updateRole(Role role);
	public List<Role> findRoles(String roleName);
}