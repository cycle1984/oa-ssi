package cycle.oa.service;

import java.util.List;

import cycle.oa.easyui.Tree;
import cycle.oa.po.MyResource;

public interface MyResourceService extends BaseService<MyResource>{

	//查找所有菜单
	public List<MyResource> findAllMenu();
	
	//获得菜单树
	public List<Tree> findMenuTree();
	
	//根据roleId查询菜单
	public List<MyResource> selectMenuByRoleId(Integer roleId);
	
	//通过roleID查询所有资源
	public List<MyResource> selectMyResourceByRoleId(Integer roleId);
}
