package cycle.oa.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.easyui.Grid;
import cycle.oa.po.MyGroup;
import cycle.oa.service.MyGroupService;

@Controller
@RequestMapping("/myGroup")
public class MyGroupController {
	
	@Resource(name="myGroupService")
	private MyGroupService myGroupService;
	
	
	/**
	 * 跳转到群组列表页面
	 * @return
	 */
	@RequestMapping("/gridJsp")
	public String gridJsp(){
		
		return "/myGroup/list";
	}
	/**
	 * 返回群组json数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/grid")
	@ResponseBody
	public Grid<MyGroup> grid() throws Exception{
		Grid<MyGroup> grid = new Grid<MyGroup>();
		List<MyGroup> myGroup = myGroupService.findAll();
		Long count = myGroupService.findCount();
		grid.setTotal(count);
		grid.setRows(myGroup);
		return grid;
	}
}
