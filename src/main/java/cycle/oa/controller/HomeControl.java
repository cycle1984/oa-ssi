package cycle.oa.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cycle.oa.po.Unit;
import cycle.oa.po.User;
import cycle.oa.service.UnitService;


/**
 * 主页跳转
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/home")
public class HomeControl {

	@Resource
	private UnitService unitService;
	
	@RequestMapping("/index.do")
	public String index(Model model){
		//从shiro的session中取activeUser
		Subject subject = SecurityUtils.getSubject();
		//取身份信息
		User userModel = (User) subject.getPrincipal();
		Unit unit = new Unit();
		if(userModel.getUnit()!=null){
			unit = unitService.selectById(userModel.getUnit().getId());
		}
		userModel.setUnit(unit);
		System.out.println(userModel);
		model.addAttribute("userModel", userModel);
		System.out.println("index");
		return "/home/index";
	}
	
	@RequestMapping("/west.do")
	public String west(){
		
		System.out.println("west");		
		return "/home/west";
	}
	
	@RequestMapping("/main.do")
	public String main(Model model){
		//从shiro的session中取activeUser
		Subject subject = SecurityUtils.getSubject();
		//取身份信息
		User userModel = (User) subject.getPrincipal();
		Unit unit = new Unit();
		if(userModel.getUnit()!=null){
			unit = unitService.selectById(userModel.getUnit().getId());
		}
		userModel.setUnit(unit);
		System.out.println(userModel);
		model.addAttribute("userModel", userModel);
		System.out.println("main");		
		return "/home/main";
	}
	
	@RequestMapping("/south.do")
	public String south(){
		System.out.println("south");		
		return "/home/south";
	}
}
