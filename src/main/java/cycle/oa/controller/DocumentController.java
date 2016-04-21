package cycle.oa.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Object save(@RequestParam(value="ids[]") Integer[] ids,@RequestParam(value="fileNewNames[]") String[] fileNewNames){
		System.out.println("ids="+ids+",names="+fileNewNames);
		
		return null;
	}
	
	@RequestMapping("/uploadFile.do")
	@ResponseBody
	public Object uploadFile(MultipartFile file){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName = sdf.format(new Date())+"-" +file.getOriginalFilename();//文件名前加上精确到毫秒的时间，防止文件名重复
		String fileBasePath ="D:/upload/";//附件存储路径
		// 写到指定的路径中
		String filePath = fileBasePath+fileName;
		File f = new File(fileBasePath);
		// 如果指定的路径没有就创建
		if (!f.exists()) {
			f.mkdirs();
		}
		try {
			File fo = new File(filePath);
			file.transferTo(fo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
	
}
