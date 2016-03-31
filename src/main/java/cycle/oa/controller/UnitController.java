package cycle.oa.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.easyui.Json;
import cycle.oa.po.Page;
import cycle.oa.po.Unit;
import cycle.oa.service.MyGroupService;
import cycle.oa.service.UnitService;

@Controller
@RequestMapping("/unit")
public class UnitController extends BaseController{

	@Resource
	private UnitService unitService;
	
	@Resource
	private MyGroupService myGroupService;
	
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
		
		System.out.println("myGroupID = "+unit.getMyGroup().getId());
		List<Unit> units =  unitService.selectListByEntity(unit);
		return units;
	}
	
	
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Json save(Unit unit){
		System.out.println(unit);
		Json json = new Json();
		unit.setCreatedateTime(new Date());
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
			unit.setUpdatedateTime(new Date());
			unitService.edit(unit);
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
}
