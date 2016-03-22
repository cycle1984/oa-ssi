package cycle.oa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.easyui.Tree;
import cycle.oa.service.MyResourceService;

@Controller
@RequestMapping("/sys")
public class SysController {

	@Resource(name="myResourceService")
	private MyResourceService myResourceService;
	
	@RequestMapping("/findAllMenu.do")
	@ResponseBody
	public List<Tree> findAllMenu() {
		List<Tree> tree = new ArrayList<Tree>();
		try {
			tree = myResourceService.findMenuTree();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tree;
	}
	
}
