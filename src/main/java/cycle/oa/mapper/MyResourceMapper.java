package cycle.oa.mapper;

import java.util.List;

import cycle.oa.po.MyResource;

public interface MyResourceMapper extends BaseMapper<MyResource> {

	//通过roleID查询所有资源
	public List<MyResource> selectMyResourceByRoleId(Integer roleId);
	//通过角色id获得所拥有的权限资源
	public List<MyResource> selectPermissionByRoleId(Integer roleId);
	//通过角色id获得所拥有的菜单资源
	public List<MyResource> selectMenuByRoleId(Integer roleId);
}
