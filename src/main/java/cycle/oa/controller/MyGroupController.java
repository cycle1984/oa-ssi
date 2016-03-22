package cycle.oa.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.easyui.Grid;
import cycle.oa.easyui.Json;
import cycle.oa.po.MyGroup;
import cycle.oa.service.MyGroupService;

@Controller
@RequestMapping("/myGroup")
public class MyGroupController extends BaseController{
	
	@Resource(name="myGroupService")
	private MyGroupService myGroupService;
	
	
	/**
	 * 跳转到群组列表页面
	 * @return
	 */
	@RequestMapping("/gridJsp.do")
	public String gridJsp(){
		
		return "/myGroup/list";
	}
	/**
	 * 返回群组json数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/grid.do")
	@ResponseBody
	public Grid<MyGroup> grid(MyGroup group) throws Exception{
		Grid<MyGroup> grid = new Grid<MyGroup>();
		List<MyGroup> myGroup = myGroupService.findAll(group);
		Long count = myGroupService.findCount();
		grid.setTotal(count);
		grid.setRows(myGroup);
		return grid;
	}
	
	@RequestMapping("/saveUI.do")
	public String saveUI(){
		
		return "/myGroup/saveUI";
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
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
	public Json edit(MyGroup group){
		
		Json json = new Json();
		try {
			myGroupService.edit(group);
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
	public Json delete(@RequestParam(value="ids[]") Integer[] ids){
		System.out.println("a"+ids);
		Json json = new Json();
		try {
			myGroupService.delete(ids);
			json.setSuccess(true);
			json.setMsg("成功删除【"+ids.length+"】条数据");
		} catch (Exception e) {
			json.setMsg("删除失败");
			e.printStackTrace();
		}
		return json;
	} 
}
