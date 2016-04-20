package cycle.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.po.Document;
import cycle.oa.po.Page;
import cycle.oa.po.SignInfo;

@Controller
@RequestMapping("/signInfo")
public class SignInfoController extends BaseController{
	
	
	/**
	 * 查看公文签收情况
	 * @param docId
	 * @param model
	 * @return
	 */
	@RequestMapping("/signInfoList.do")
	public String signInfoList(Integer docId,Model model){
		
		Document document = documentService.selectById(docId);
		model.addAttribute("document", document);
		
		return "/signInfo/signInfoList";
	}
	
	/**
	 * 根据公文id查询签收信息grid数据
	 * @param docId
	 * @return
	 */
	@RequestMapping("/gridBydocId.do")
	@ResponseBody
	public Object gridBydocId(Page<SignInfo> page,SignInfo signInfo){
		Page<SignInfo> p = signInfoService.selectPageDyc(page);
		
		return p.getPageMap();
	}
	
}
