package cycle.oa.controller;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.easyui.Json;
import cycle.oa.po.Page;
import cycle.oa.po.Unit;
import cycle.oa.po.MyGroup;
import cycle.oa.easyui.TreeNode;

@Controller
@RequestMapping("/unit")
public class UnitController extends BaseController{

	/**
	 * //通过关键字分页查询
	 * 返回群组json数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/grid.do")
	@ResponseBody
	public Object grid(Page<Unit> page,Unit unit) throws Exception{
		if(unit.getName()!=null){//如果name属性不为空， 模糊查询
			unit.setName("%"+unit.getName()+"%");
		}
		page.setParamEntity(unit);//将后台数据传到page模型中
		
		Page<Unit> p = unitService.selectPageDyc(page);
		return p.getPageMap();
	}
	
	@RequestMapping("/getUnitsByMyGroupId.do")
	@ResponseBody
	public Object getUnitsByMyGroupId(Unit unit) throws Exception{
		
		List<Unit> units =  unitService.selectListByEntity(unit);
		return units;
	}
	
	
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Json save(Unit unit){
		Json json = new Json();
		unit.setCreateDatetime(new Date());
		try {
			unitService.save(unit);
			unit.setMyGroup(myGroupService.selectById(unit.getMyGroup().getId()));
			json.setSuccess(true);
			json.setObj(unit);
			json.setMsg("新建单位【"+unit.getName()+"】成功");
		} catch (Exception e) {
			json.setMsg("新建单位失败");
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/edit.do")
	@ResponseBody
	public Json edit(Unit unit){
		
		Json json = new Json();
		try {
			unit.setUpdateDatetime(new Date());
			unitService.update(unit);
			json.setSuccess(true);
			json.setMsg("修改单位信息【"+unit.getName()+"】成功");
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
			unitService.deleteByArray(ids);
			json.setSuccess(true);
			json.setMsg("成功删除【"+ids.length+"】条数据");
		} catch (Exception e) {
			json.setMsg("删除失败");
			e.printStackTrace();
		}
		return json;
	}
	
	
	
	/**
	 * 获得收文单位树形复选框菜单，按首字母排序
	 * @return
	 */
	@RequestMapping("/getUnitTree.do")
	@ResponseBody
	public Object getUnitTree(){
		List<TreeNode> tree = new ArrayList<TreeNode>();//用于放树的顶点
		TreeNode node = new TreeNode();//顶点
		node.setText("全选");//顶点名称
		node.setId(0);//设置顶点的id,0为不会用到的点
		
		List<TreeNode> treeMyGroup = new ArrayList<TreeNode>();//用于二级节点（mygroup群组分组节点）
		Map<String, Object> map = new HashMap<String, Object>();
		List<MyGroup> myGroups = myGroupService.selectAll(map);//获得mygroup群组
		for (MyGroup myGroup : myGroups) {
			TreeNode nodeGroup = new TreeNode();//二级节点
			nodeGroup.setText(myGroup.getName());
			nodeGroup.setId(myGroup.getId());
			nodeGroup.setState("closed");//二级菜单默认不展开
			
			List<TreeNode> treeUnit = new ArrayList<TreeNode>();//用于三级节点（unit群组分组节点）
			Unit entity = new Unit();
			entity.setMyGroup(myGroup);
			List<Unit> units = unitService.selectListByEntity(entity);//获得当前机构、群组下的所有单位
			for (Unit unit : units) {
				if(unit.getState()==0){//state=0的才允许接收公文
					TreeNode nodeUnit = new TreeNode();//三级节点
					nodeUnit.setText(unit.getName());
					nodeUnit.setId(unit.getId());
					//将单位全称设置到节点的title，用户鼠标悬停时的提示信息
					Map<String,String> attributes = new HashMap<String,String>();
					attributes.put("title", unit.getFullName());
					nodeUnit.setAttributes(attributes);
					
					treeUnit.add(nodeUnit);//添加到三级节点树
					
					nodeGroup.setChildren(treeUnit);//将节点设置为当前机构的三级节点
				}
			}
			//单位列表按照首字母排序
			Comparator<TreeNode> cmp = new Comparator<TreeNode>() {  
				  
			    public int compare(TreeNode o1, TreeNode o2) {  
			        Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);  
			  
			        // start在这里实现你的比较 
			        String[] strs1 = new String[2];  
			        strs1[0] = o1.getText();  
			        strs1[1] = o2.getText();  
			  
			        String[] strs2 = new String[2];  
			        strs2[0] = o1.getText();  
			        strs2[1] = o2.getText();  
			  
			        Arrays.sort(strs1, cmp);  
			        Arrays.sort(strs2, cmp);  
			  
			        if (strs1[0].equals(strs1[1])) {  
			            if (strs2[0].equals(strs2[1])) {  
			                return 0;  
			            }  
			  
			            if (strs2[0].equals(o1.getText())) {  
			                return -1;  
			            } else {  
			                return 1;  
			            }  
			        } else {  
			            if (strs1[0].equals(o1.getText())) {  
			                return -1;  
			            } else if (strs1[0].equals(o2.getText())) {  
			                return 1;  
			            }  
			        }  
			  
			        // end在这里实现你的比较  
			  
			        return 0;  
			    }  
			};  
			Collections.sort(treeUnit,cmp);
			if(nodeGroup.getChildren()!=null){//如果当前机构存在单位（既当前二级节点的子节点不为空）
				treeMyGroup.add(nodeGroup);//将此节点设置为树的二级节点
			}
		}
		
		node.setChildren(treeMyGroup);//将二级节点集合设为顶点的二级节点
		tree.add(node);
		
		return tree;
	}
	

	/**
	 * 获得发文单位树形复选框菜单，按首字母排序
	 * @return
	 */
	@RequestMapping("/findMyGroupAndUnitTree.do")
	@ResponseBody
	public Object findMyGroupAndUnitTree(){
		List<TreeNode> treeMyGroup = new ArrayList<TreeNode>();//用于二级节点（mygroup群组分组节点）
		Map<String, Object> map = new HashMap<String, Object>();
		List<MyGroup> myGroups = myGroupService.selectAll(map);//获得mygroup群组
		for (MyGroup myGroup : myGroups) {
			TreeNode nodeGroup = new TreeNode();//二级节点
			nodeGroup.setText(myGroup.getName());
			nodeGroup.setId(myGroup.getId());
			nodeGroup.setState("closed");//二级菜单默认不展开
			
			List<TreeNode> treeUnit = new ArrayList<TreeNode>();//用于三级节点（unit群组分组节点）
			Unit entity = new Unit();
			entity.setMyGroup(myGroup);
			List<Unit> units = unitService.selectListByEntity(entity);//获得当前机构、群组下的所有单位
			for (Unit unit : units) {
				if(unit.getState()==0){//state=0的才允许接收公文
					TreeNode nodeUnit = new TreeNode();//三级节点
					nodeUnit.setText(unit.getName());
					nodeUnit.setId(unit.getId());
					//将单位全称设置到节点的title，用户鼠标悬停时的提示信息
					Map<String,String> attributes = new HashMap<String,String>();
					attributes.put("title", unit.getFullName());
					nodeUnit.setAttributes(attributes);
					
					treeUnit.add(nodeUnit);//添加到三级节点树
					
					nodeGroup.setChildren(treeUnit);//将节点设置为当前机构的三级节点
				}
			}
			//单位列表按照首字母排序
			Comparator<TreeNode> cmp = new Comparator<TreeNode>() {  
				  
			    public int compare(TreeNode o1, TreeNode o2) {  
			        Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);  
			  
			        // start在这里实现你的比较 
			        String[] strs1 = new String[2];  
			        strs1[0] = o1.getText();  
			        strs1[1] = o2.getText();  
			  
			        String[] strs2 = new String[2];  
			        strs2[0] = o1.getText();  
			        strs2[1] = o2.getText();  
			  
			        Arrays.sort(strs1, cmp);  
			        Arrays.sort(strs2, cmp);  
			  
			        if (strs1[0].equals(strs1[1])) {  
			            if (strs2[0].equals(strs2[1])) {  
			                return 0;  
			            }  
			  
			            if (strs2[0].equals(o1.getText())) {  
			                return -1;  
			            } else {  
			                return 1;  
			            }  
			        } else {  
			            if (strs1[0].equals(o1.getText())) {  
			                return -1;  
			            } else if (strs1[0].equals(o2.getText())) {  
			                return 1;  
			            }  
			        }  
			  
			        // end在这里实现你的比较  
			  
			        return 0;  
			    }  
			};  
			Collections.sort(treeUnit,cmp);
			if(nodeGroup.getChildren()!=null){//如果当前机构存在单位（既当前二级节点的子节点不为空）
				treeMyGroup.add(nodeGroup);//将此节点设置为树的二级节点
			}
		}
		
		return treeMyGroup;
		
	}
}
