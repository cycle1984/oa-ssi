package cycle.oa.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import cycle.oa.po.Role;
import cycle.oa.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

	@Override
	public void deleleteRoleMyResourceByRoleId(Integer roleId) {
		roleMapper.deleleteRoleMyResourceByRoleId(roleId);
		
	}

	@Override
	public void insertRoleMyResource(Map<String, Integer> map) {
		roleMapper.insertRoleMyResource(map);
	}

}
