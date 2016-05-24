package cycle.oa.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cycle.oa.po.News;
import cycle.oa.po.Page;
import cycle.oa.po.Unit;
import cycle.oa.po.User;

/**
 * 信息资讯控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController{

	/**
	 * 获得所有信息资讯
	 * @param page
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/grid.do")
	public Object grid(Page<News> page , News news){
		if(news.getTitle()!=null){//如果name属性不为空， 模糊查询
			news.setTitle("%"+news.getTitle()+"%");
		}
		page.setParamEntity(news);//将后台数据传到page模型中
		
		Page<News> p = newsService.selectPageDyc(page);
		return p.getPageMap();
	}
	
	@ResponseBody
	@RequestMapping("/myGrid.do")
	public Object myGrid(Page<News> page , News news){
		Subject subject = SecurityUtils.getSubject();
		//取身份信息
		User user = (User) subject.getPrincipal();
		if(!user.isAdmin()){//不是管理员
			Unit unit = user.getUnit();
			if(unit!=null){
				news.setUnit(unit);
			}
		}
		if(news.getTitle()!=null){//如果name属性不为空， 模糊查询
			news.setTitle("%"+news.getTitle()+"%");
		}
		page.setParamEntity(news);//将后台数据传到page模型中
		
		Page<News> p = newsService.selectPageDyc(page);
		return p.getPageMap();
	}
	
	@RequestMapping("/save.do")
	public Object save(News news){
		
		return null;
	}
	
	@RequestMapping("/uploadImg.do")
	public void uploadImg(MultipartFile upload,String CKEditorFuncNum,HttpServletRequest request,HttpServletResponse response) throws IOException{// CKEditor提交的很重要的一个参数  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String expandedName = "";  //文件扩展名  
		String uploadContentType = upload.getContentType();
        if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")) {  
            //IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg  
            expandedName = ".jpg";  
        }else if(uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")){  
            //IE6上传的png图片的headimageContentType是"image/x-png"  
            expandedName = ".png";  
        }else if(uploadContentType.equals("image/gif")){  
            expandedName = ".gif";  
        }else if(uploadContentType.equals("image/bmp")){  
            expandedName = ".bmp";  
        }else{  
            out.println("<script type=\"text/javascript\">");    
            out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");   
            out.println("</script>");  
        }  
          
        if(upload.getSize() > 6000*1024){  
            out.println("<script type=\"text/javascript\">");    
            out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",''," + "'文件大小不得大于6000k');");   
            out.println("</script>");  
        }  
          
          
        String uploadPath =  request.getSession().getServletContext().getRealPath("/")+"newsImg/";     
        String contextPath = request.getSession().getServletContext().getContextPath();
        System.out.println(contextPath);
        String fileName = java.util.UUID.randomUUID().toString();  //采用时间+UUID的方式随即命名  
        fileName += expandedName;
        File f = new File(uploadPath);
		// 如果指定的uploadPath路径没有就创建
		if (!f.exists()) {
			f.mkdirs();
		}
		try {
			File fo = new File(uploadPath+fileName);
			upload.transferTo(fo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		  // 返回“图像”选项卡并显示图片  
        out.println("<script type=\"text/javascript\">");    
        out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + contextPath+"/newsImg/" + fileName + "','')");    
        out.println("</script>");  
	}
}
