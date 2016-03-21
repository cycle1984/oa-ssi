package cycle.oa.service;

import java.util.List;

import cycle.oa.easyui.Tree;
import cycle.oa.po.MyResource;

public interface MyResourceService {

	//查找所以菜单
	public List<MyResource> findAllMenu() throws Exception;
	
	//获得菜单树
	public List<Tree> findMenuTree() throws Exception;
}
