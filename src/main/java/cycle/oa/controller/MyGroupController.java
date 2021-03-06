package cycle.oa.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.easyui.Json;
import cycle.oa.po.MyGroup;
import cycle.oa.po.Page;

@Controller
@RequestMapping("/myGroup")
public class MyGroupController extends BaseController{
	
	/**
	 * //通过关键字分页查询
	 * 返回群组json数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/grid.do")
	@ResponseBody
	@RequiresPermissions("myGroup:grid")
	public Object grid(Page<MyGroup> page,MyGroup group) throws Exception{
		if(group.getName()!=null){//如果name属性不为空， 模糊查询
			group.setName("%"+group.getName()+"%");
		}
		page.setParamEntity(group);//将后台数据传到page模型中
		
		Page<MyGroup> p = myGroupService.selectPageDyc(page);
		return p.getPageMap();
	}
	
	/**
	 * 查询所有群组信息
	 * 返回群组json数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findAll.do")
	@ResponseBody
	public List<MyGroup> findAll(Page<MyGroup> page,MyGroup group) throws Exception{
		List<MyGroup> list = myGroupService.findAll(group);
		return list;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	@RequiresPermissions("myGroup:save")
	public Json save(MyGroup group){
		
		Json json = new Json();
		try {
			myGroupService.save(group);
			json.setSuccess(true);
			json.setObj(group);
			json.setMsg("新建机构【"+group.getName()+"】成功");
		} catch (Exception e) {
			json.setMsg("新建失败");
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/edit.do")
	@ResponseBody
	@RequiresPermissions("myGroup:edit")
	public Json edit(MyGroup group){
		
		Json json = new Json();
		try {
			myGroupService.update(group);
			json.setSuccess(true);
			json.setMsg("修改机构【"+group.getName()+"】成功");
		} catch (Exception e) {
			json.setMsg("修改失败");
			e.printStackTrace();
		}
		return json;
	} 
	
	@RequestMapping("/delete.do")
	@ResponseBody
	@RequiresPermissions("myGroup:delete")
	public Json delete(@RequestParam(value="ids[]") Integer[] ids){
		Json json = new Json();
		try {
			myGroupService.deleteByArray(ids);
			json.setSuccess(true);
			json.setMsg("成功删除【"+ids.length+"】条数据");
		} catch (Exception e) {
			json.setMsg("删除失败,请检查该数据是否有下级数据！");
			e.printStackTrace();
		}
		return json;
	}
}
