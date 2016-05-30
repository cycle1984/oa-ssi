package cycle.oa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.easyui.Json;
import cycle.oa.po.SysBase;

@Controller
@RequestMapping("/sysBase")
public class SysBaseController extends BaseController {

	@RequestMapping("/config.do")
	public String config(Model model){
		
		Map<String, Object> map = null;
		List<SysBase> sysList = sysBaseService.selectAll(map);
		if(sysList.size()>0){
			SysBase sysBase = sysList.get(0);
			model.addAttribute("sysBase", sysBase);
		}
		return "/sysBase/config";
	}
	
	@RequestMapping("/edit.do")
	@ResponseBody
	public Object edit(SysBase sysBase){
		Json j = new Json();
		try {
			sysBaseService.update(sysBase);
			j.setSuccess(true);
			j.setMsg("配置成功!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			j.setMsg("配置失败!");
			e.printStackTrace();
		}
		return j;
	}
}
