package cycle.oa.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.easyui.Json;
import cycle.oa.po.MyGroup;
import cycle.oa.po.Page;
import cycle.oa.po.Unit;
import cycle.oa.po.User;
import cycle.oa.service.MyGroupService;
import cycle.oa.service.UnitService;
import cycle.oa.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Resource
	private MyGroupService myGroupService;
	
	@Resource
	private UnitService unitService;
	
	@Resource
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
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
	
	/**
	 * 注册功能里的根据登陆名称查询是否存在
	 * 
	 */
	@RequestMapping("/searchByLoginName.do")
	@ResponseBody
	public Object searchByLoginName(User user) throws IOException{
		String loginN = user.getLoginName().trim();//去掉前后空格
		if(loginN!=null||!"".equals(loginN)){
			User u = userService.selectEntity(user);
			if(u!=null){
				return "false";
			}else{
				return "true";
			}
		}else{
			return "false";
		}
	}
	
	/**
	 * 
	 * 登录
	 * @return
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public Json login(HttpSession session, User user){
		Json json = new Json();
		//查询出用户表
		user.setPwd(DigestUtils.md5Hex(user.getPwd()));
		User u = userService.selectEntity(user);
		if(u!=null){
			if(u.getUnit()!=null){
				//查询出所属单位
				Unit unit = unitService.selectById(u.getUnit().getId());
				if(unit!=null){
					//查询出所属机构
					MyGroup m = myGroupService.selectById(unit.getId());
					unit.setMyGroup(m);
				}
				u.setUnit(unit);
			}
			//session
			session.setAttribute("userSession", u);
			
			json.setSuccess(true);
			json.setMsg("登录成功！");
		}else{
			json.setMsg("登录失败，用户名或密码错误!");
		}
		return json;
	}
	
	/**
	 * 
	 * 登出
	 * @return
	 */
	@RequestMapping("/logout.do")
	@ResponseBody
	public Json logout(HttpSession session, User user){
		session.removeAttribute("userSession");
		Json j = new Json();
		j.setSuccess(true);
		return j;
	}
	
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Json save(User user){
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
	public Json delete(@RequestParam(value="ids[]",required=true) Integer[] ids){
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
