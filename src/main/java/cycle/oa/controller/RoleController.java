package cycle.oa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.easyui.Json;
import cycle.oa.easyui.TreeNode;
import cycle.oa.po.MyResource;
import cycle.oa.po.Page;
import cycle.oa.po.Role;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@RequestMapping("/grid.do")
	@ResponseBody
	public Object grid(Page<Role> page,Role role){
		if(role.getName()!=null){//如果name属性不为空， 模糊查询
			role.setName("%"+role.getName()+"%");
		}
		page.setParamEntity(role);//将后台数据传到page模型中
		Page<Role> p = roleService.selectPageDyc(page);
		return p.getPageMap();
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Json save(Role role){
		
		Json json = new Json();
		try {
			roleService.save(role);
			json.setSuccess(true);
			json.setObj(role);
			json.setMsg("新建机构【"+role.getName()+"】成功");
		} catch (Exception e) {
			json.setMsg("新建失败");
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/edit.do")
	@ResponseBody
	public Json edit(Role role){
		
		Json json = new Json();
		try {
			roleService.update(role);
			json.setSuccess(true);
			json.setMsg("修改机构【"+role.getName()+"】成功");
		} catch (Exception e) {
			json.setMsg("修改失败");
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public Json delete(@RequestParam(value="ids[]") Integer[] ids){
		Json json = new Json();
		try {
			roleService.deleteByArray(ids);
			json.setSuccess(true);
			json.setMsg("成功删除【"+ids.length+"】条数据");
		} catch (Exception e) {
			json.setMsg("删除失败,请检查该数据是否有下级数据！");
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 
	 * 获得所有的资源列表(转换成树，显示到前端)
	 */
	@RequestMapping("/setMyResourceUI.do")
	@ResponseBody
	public Object setMyResourceUI() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MyResource> myResources = myResourceService.selectAll(map);//获得所有的权限（资源）
		List<TreeNode> tree = new ArrayList<TreeNode>();//创建一棵树
		for (MyResource resource : myResources) {
			TreeNode node = new TreeNode();//节点
			BeanUtils.copyProperties(resource, node);//将权限（资源）赋值到树节点中
			node.setText(resource.getName());//设置树节点显示名称
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", resource.getUrl());//设置url
			attributes.put("target", resource.getTarget());
			node.setAttributes(attributes);
			MyResource cy = resource.getParetResource();//获取当前权限（资源）的上级权限（资源）（父节点）
			if (cy != null) {
				node.setPid(cy.getId());//设置当前节点的上节点
			}
			tree.add(node);
		}
		return tree;
	}
	
	/**
	 * 获得角色所拥有的资源（权限）,用于勾选当前角色（权限组）所拥有的
	 */
	@RequestMapping("/getRoleMyResource.do")
	@ResponseBody
	public Object getRoleMyResource(Integer roleId){
		List<MyResource> resources = new ArrayList<MyResource>();
		if (roleId != null) {
			resources = myResourceService.selectMyResourceByRoleId(roleId);//获得角色所拥有的资源（权限）
		}
		return resources;
	}
	
	/**
	 * 设置角色的权限（资源）
	 * @throws Exception
	 */
	@RequestMapping("/setMyResource.do")
	@ResponseBody
	public Object setMyResource(@RequestParam(value="ids[]",required=false) Integer[] ids,Integer roleId) {
		Json json = new Json();
		if(roleId!=null){
			
			//从数据库中获取原对象
			Role role = roleService.selectById(roleId);
			roleService.deleleteRoleMyResourceByRoleId(roleId);//删除掉旧的角色和权限资源关系
			String cyResouceString = "";
			if(ids!=null){
				List<MyResource> mrs = myResourceService.selectByIds(ids);//根据勾选的权限（资源）id获取所有的权限（资源）
				
				for (MyResource resource : mrs) {
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("myResourceId", resource.getId());
					map.put("roleId", roleId);
					roleService.insertRoleMyResource(map);
					if(resource.getMyResources().size()==0){//如果是叶子权限
						cyResouceString=cyResouceString+resource.getName()+",";//把所有权限的名称转换成字符串，拥有显示权限组（角色）的备注信息
						
					}
				}
				
			}
			if("".equals(cyResouceString)){
				cyResouceString="无任何权限";
			}
			role.setRemark(cyResouceString);
			
			try {
				roleService.update(role);//更新
				json.setSuccess(true);
				json.setMsg("权限组【"+role.getName()+"】设置成功");
			} catch (Exception e) {
				json.setMsg("权限组修改失败");
				e.printStackTrace();
			}
		}
		return json;
	}
}
