package cycle.oa.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.easyui.Json;
import cycle.oa.po.Page;
import cycle.oa.po.Unit;
import cycle.oa.po.User;
import cycle.oa.service.UnitService;
import cycle.oa.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Resource
	private UnitService unitService;
	
	@Resource
	private UserService userService;
	
	/**
	 * //通过关键字分页查询
	 * 返回群组json数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/grid.do")
	@ResponseBody
	public Object grid(Page<User> page,User user) throws Exception{
		if(user.getName()!=null){//如果name属性不为空， 模糊查询
			user.setName("%"+user.getName()+"%");
		}
		page.setParamEntity(user);//将后台数据传到page模型中
		
		Page<User> p = userService.selectPageDyc(page);
		return p.getPageMap();
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Json save(User user){
		System.out.println(user);
		Json json = new Json();
		user.setCreatedateTime(new Date());
		try {
			userService.save(user);
			user.setUnit(unitService.selectById(user.getUnit().getId()));
			json.setSuccess(true);
			json.setObj(user);
			json.setMsg("新建用户【"+user.getName()+"】成功");
		} catch (Exception e) {
			json.setMsg("新建用户失败");
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/edit.do")
	@ResponseBody
	public Json edit(User user){
		
		Json json = new Json();
		try {
			user.setUpdatedateTime(new Date());
			userService.edit(user);
			json.setSuccess(true);
			json.setMsg("修改用户信息【"+user.getName()+"】成功");
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
			userService.deleteByArray(ids);
			json.setSuccess(true);
			json.setMsg("成功删除【"+ids.length+"】条数据");
		} catch (Exception e) {
			json.setMsg("删除失败");
			e.printStackTrace();
		}
		return json;
	} 
}
