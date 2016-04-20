package cycle.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.po.Document;
import cycle.oa.po.Page;

@Controller
@RequestMapping("/document")
public class DocumentController extends BaseController {
	
	/**
	 * 返回公文列表grid json数据
	 * @return
	 */
	@RequestMapping("/publishGrid.do")
	@ResponseBody
	public Object publishGrid(Page<Document> page,Document document){
		String documentTitle = document.getDocumentTitle();
		if(documentTitle!=null){
			document.setDocumentTitle("%"+documentTitle+"%");
		}
		
		page.setParamEntity(document);
		Page<Document> p = documentService.selectPageDyc(page);
		
		return p.getPageMap();
	}
	
	
	
}
