package cycle.oa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cycle.oa.easyui.Json;
import cycle.oa.po.Document;
import cycle.oa.po.MyFile;
import cycle.oa.po.Page;
import cycle.oa.po.SignInfo;
import cycle.oa.po.User;

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
	public Object save(@RequestParam(value="ids[]") Integer[] ids,@RequestParam(value="fileNewNames[]") String[] fileNewNames,Document document){
		Json json = new Json();
		document.setCreateDatetime(new Date());//创建时间
		Subject subject = SecurityUtils.getSubject();
		//取身份信息
		User user = (User) subject.getPrincipal();
		//设置发布人姓名
		document.setPublishUserName(user.getName());
		if(user.getUnit()!=null){//设置发布单位
			document.setPublishUnit(user.getUnit());
		}
		try {
			//将document信息保存到数据库
			documentService.save(document);
			
			//myfile
			for (String fileNewName : fileNewNames) {
				MyFile myFile = new MyFile();
				myFile.setFileName(fileNewName);
				myFile.setDocument(document);
				//将myfile保存到数据库
				myFileService.save(myFile);
			}
			
			//签收信息表
			for (Integer id : ids) {
				SignInfo signInfo = new SignInfo();
				//设置所属的公文
				signInfo.setDocument(document);
				//设置收文的单位
				signInfo.setSignUnit(unitService.selectById(id));
				
				signInfoService.save(signInfo);
			}
			
			json.setSuccess(true);
			json.setMsg("公文发布成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			json.setMsg("发布失败!");
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 附件上传
	 * produces="text/html;charset=UTF-8"，解决返回的json数据中文乱码问题
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/uploadFile.do",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object uploadFile(MultipartFile file){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName = sdf.format(new Date())+"-" +file.getOriginalFilename();//文件名前加上精确到毫秒的时间，防止文件名重复
		String fileBasePath ="D:/upload/";//附件存储路径
		// 写到指定的路径中
		String filePath = fileBasePath+fileName;
		File f = new File(fileBasePath);
		// 如果指定的D:/upload/路径没有就创建
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
	
	@RequestMapping("/fileDown.do")
	public String fileDown(Integer fileId,HttpServletRequest request,
            HttpServletResponse response){
		
		MyFile myFile = myFileService.selectById(fileId);
		String fileName = myFile.getFileName();
		int i =fileName.indexOf("-");
		String fileFileName = fileName.substring(i+1, fileName.length());
		
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        try {
			response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileFileName.getBytes("utf-8"), "ISO8859-1"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			String basePath = "D:/upload/";
			InputStream inputStream = new FileInputStream(new File(basePath
			        + fileName));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
 
             // 这里主要关闭。
            os.close();
 
            inputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}
}
