package cycle.oa.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cycle.oa.service.UnitService;

@Controller
@RequestMapping("/base")
public class BaseController {
	
	//跳到指定页面
	@RequestMapping("/goURL/{folder}/{file}")
	public String goURL(@PathVariable String folder,@PathVariable String file) {
		System.out.println("goURL.folder|file===" + folder+"/"+file);
		return "/"+folder+"/"+file;
	}
}
