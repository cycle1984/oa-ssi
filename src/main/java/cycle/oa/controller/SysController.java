package cycle.oa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.easyui.Tree;
import cycle.oa.po.MyResource;
import cycle.oa.po.User;
import cycle.oa.service.MyResourceService;

@Controller
@RequestMapping("/sys")
public class SysController {

	@Resource(name="myResourceService")
	private MyResourceService myResourceService;
	
	@RequestMapping("/findAllMenu.do")
	@ResponseBody
	public List<Tree> findAllMenu() {
		Subject subject = SecurityUtils.getSubject();
		
		User user = (User) subject.getPrincipal();
		List<Tree> tree = new ArrayList<Tree>();//准备菜单树
		List<MyResource> myResources = new ArrayList<MyResource>();
		if(user.isAdmin()){
			myResources = myResourceService.findAllMenu();
		}else{
			if(user.getRole()!=null){
				myResources = myResourceService.selectMenuByRoleId(user.getRole().getId());
			}
		}
		for (MyResource myResource : myResources) {
			Tree node = new Tree();//树节点
			BeanUtils.copyProperties(myResource, node);//将菜单项复制到树节点
			node.setText(myResource.getName());//设置树节点的名称
			Map<String,String> attributes = new HashMap<String,String>();
			attributes.put("url", myResource.getUrl());//菜单项对应的URL
			attributes.put("target", myResource.getTarget());
			node.setAttributes(attributes);
			MyResource cy = myResource.getParetResource();
			if(cy!=null){//存在上级节点的情况
				node.setPid(cy.getId());//设置上级节点ID
			}
			tree.add(node);
		}
		
		return tree;
	}
	
}
