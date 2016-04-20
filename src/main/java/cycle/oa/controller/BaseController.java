package cycle.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cycle.oa.service.DocumentService;
import cycle.oa.service.MyFileService;
import cycle.oa.service.MyGroupService;
import cycle.oa.service.MyResourceService;
import cycle.oa.service.RoleService;
import cycle.oa.service.SignInfoService;
import cycle.oa.service.UnitService;
import cycle.oa.service.UserService;

@Controller
@RequestMapping("/base")
public class BaseController {
	
	@Autowired
	protected MyGroupService myGroupService;
	
	@Autowired
	protected UnitService unitService;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected RoleService roleService;
	
	@Autowired
	protected MyResourceService myResourceService;
	
	@Autowired
	protected DocumentService documentService;
	
	@Autowired
	protected SignInfoService signInfoService;
	
	@Autowired
	protected MyFileService myFileService;
	
	//跳到指定页面
	@RequestMapping("/goURL/{folder}/{file}")
	public String goURL(@PathVariable String folder,@PathVariable String file) {
		System.out.println("goURL.folder|file===" + folder+"/"+file);
		return "/"+folder+"/"+file;
	}
}
