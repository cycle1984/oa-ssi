package cycle.oa.service;

import java.util.Map;

import cycle.oa.po.Role;

public interface RoleService extends BaseService<Role> {

	//根据roleId删除角色和权限关系表
	public void deleleteRoleMyResourceByRoleId(Integer roleId);
	
	//新建Role_MyResource关系表关联数据 
	public void insertRoleMyResource(Map<String, Integer> map); 
}
