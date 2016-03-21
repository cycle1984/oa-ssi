package cycle.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cycle.oa.easyui.Tree;
import cycle.oa.mapper.MyResourceMapper;
import cycle.oa.po.MyResource;
import cycle.oa.service.MyResourceService;

@Service("myResourceService")
public class MyResourceServiceImpl implements MyResourceService {

	@Autowired
	private MyResourceMapper myresourceMapper;
	
	@Override
	public List<MyResource> findAllMenu() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", 0);
		return myresourceMapper.selectAll(map);
	}

	@Override
	public List<Tree> findMenuTree() throws Exception {
		List<Tree> tree = new ArrayList<Tree>();//准备菜单树
		//获取数据库里的所以菜单资源
		List<MyResource> myResources = findAllMenu();
		for (MyResource myResource : myResources) {
			Tree node = new Tree();//树节点
			BeanUtils.copyProperties(myResource, node);//将菜单项复制到树节点
			node.setText(myResource.getName());//设置树节点的名称
			Map<String,String> attributes = new HashMap<String,String>();
			attributes.put("url", myResource.getUrl());//菜单项对应的URL
			attributes.put("target", myResource.getTarget());
			node.setAttributes(attributes);
			MyResource cy = myResource.getMyResource();
			if(cy!=null){//存在上级节点的情况
				node.setPid(cy.getId());//设置上级节点ID
			}
			tree.add(node);
		}
		return tree;
	}
	
	

}
