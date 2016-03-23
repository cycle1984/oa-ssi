package cycle.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 主页跳转
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/home")
public class HomeControl {

	@RequestMapping("/index.do")
	public String index(){
		System.out.println("index");		
		return "/home/index";
	}
	
	@RequestMapping("/west.do")
	public String west(){
		System.out.println("west");		
		return "/home/west";
	}
	
	@RequestMapping("/main.do")
	public String main(){
		System.out.println("main");		
		return "/home/main";
	}
	
	@RequestMapping("/south.do")
	public String south(){
		System.out.println("south");		
		return "/home/south";
	}
}