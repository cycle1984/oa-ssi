package cycle.oa.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.easyui.Json;
import cycle.oa.po.Document;
import cycle.oa.po.MyFile;
import cycle.oa.po.Page;
import cycle.oa.po.SignInfo;
import cycle.oa.po.Unit;
import cycle.oa.po.User;
import cycle.oa.utils.IpUtil;

@Controller
@RequestMapping("/signInfo")
public class SignInfoController extends BaseController{
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	/**
	 * 收文列表，根据传过来的state参数，查询待办(false)和已办(true)公文列表
	 * @param state
	 * @return
	 */
	@RequestMapping("/receiveListGrid.do")
	@ResponseBody
	public Object receiveListGrid(Page<SignInfo> page,SignInfo signInfo){
		Subject subject = SecurityUtils.getSubject();
		//取身份信息
		User userModel = (User) subject.getPrincipal();
		Unit unit =userModel.getUnit(); 
		if(unit!=null){//如果单位不为空，查询所属签收单位的签收表
			signInfo.setSignUnit(unit);
		}
		if(signInfo.getDocument()!=null){
			//根据公文标题查询
			if(signInfo.getDocument().getDocumentTitle()!=null){
				signInfo.getDocument().setDocumentTitle("%"+signInfo.getDocument().getDocumentTitle()+"%");
			}
			//根据来文单位查询
			if(signInfo.getDocument().getPublishUnit()!=null){
				if(signInfo.getDocument().getPublishUnit().getName()!=null){
					signInfo.getDocument().getPublishUnit().setName("%"+signInfo.getDocument().getPublishUnit().getName()+"%");
				}
			}
		}
		page.setParamEntity(signInfo);
		Page<SignInfo> p = signInfoService.selectPageDyc(page);
		
		return p.getPageMap();
	}
	
	/**
	 * 查看公文签收情况
	 * @param docId
	 * @param model
	 * @return
	 */
	@RequestMapping("/signInfoList.do")
	public String signInfoList(Integer docId,Model model){
		//根据公文id获得document信息
		Document document = documentService.selectById(docId);
		
		//根据公文id获得所属的附件
		List<MyFile> myFiles = myFileService.selectByDocId(docId);
		
		document.setMyFiles(myFiles);
		
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
	public Object gridBydocId(Page<SignInfo> page,SignInfo signInfo,Integer docId){
		//设置公文的id
		Document document = new Document();
		if(docId!=null){
			document.setId(docId);
			signInfo.setDocument(document);
			signInfo.setState(null);//设置为null，已签收和未签收都查询
		}
		page.setParamEntity(signInfo);
		Page<SignInfo> p = signInfoService.selectPageDyc(page);
		
		return p.getPageMap();
	}
	
	/**
	 * 公用方法，验证输入的密码是否是当前登录用户密码
	 * @param pwd
	 * @return
	 */
	private Boolean checkPassword(String pwd){
		Subject subject = SecurityUtils.getSubject();
		//取身份信息
		User user = (User) subject.getPrincipal();
		String pwdMD5 = DigestUtils.md5Hex(pwd);
		if(pwdMD5.equals(user.getPwd())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 签收公文
	 * @return
	 */
	@RequestMapping("/signDocument")
	@ResponseBody
	public Object signDocument(String pwd,Integer id,HttpServletRequest request){
		Json json = new Json();
		
		Subject subject = SecurityUtils.getSubject();
		//取身份信息
		User user = (User) subject.getPrincipal();
		if(checkPassword(pwd)){
			//根据签收信息的id查出签收表
			SignInfo signInfo = signInfoService.selectById(id);
			if(user.getUnit()!=null&&user.getUnit().getId().equals(signInfo.getSignUnit().getId())){//只能签收属于本单位的公文
				//设置为已签收
				signInfo.setState(true);
				//签收人姓名
				signInfo.setSignUserName(user.getName());
				//签收日期
				signInfo.setSignDate(new Date());
				//签收IP
				signInfo.setIp(IpUtil.getIpAddr(request));
				try {
					signInfoService.update(signInfo);
					json.setSuccess(true);
					json.setMsg("签收成功！！！");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					json.setMsg("签收失败！！！");
					e.printStackTrace();
				}
			}else {
				json.setMsg("您只能签收本单位的公文！！！");
			}
			
		}else{
			json.setMsg("您输入的密码错误！！！");
		}
		return json;
	}
	
	/**
	 * 签收所有未签收的公文
	 * @return
	 */
	@RequestMapping("/signAllDocument")
	@ResponseBody
	public Object signAllDocument(String pwd,HttpServletRequest request){
		Json json = new Json();
		
		Subject subject = SecurityUtils.getSubject();
		//取身份信息
		User user = (User) subject.getPrincipal();
		if(checkPassword(pwd)){
			
			
			if(user.getUnit()!=null){//只能签收属于本单位的公文
				//查询所有属于本单位未签收的公文
				SignInfo entity = new SignInfo();
				entity.setState(false);
				entity.setSignUnit(user.getUnit());
				List<SignInfo> signInfos = signInfoService.selectListByEntity(entity);
				
				for (SignInfo signInfo : signInfos) {
					//设置为已签收
					signInfo.setState(true);
					//签收人姓名
					signInfo.setSignUserName(user.getName());
					//签收日期
					signInfo.setSignDate(new Date());
					//签收IP
					signInfo.setIp(IpUtil.getIpAddr(request));
					try {
						signInfoService.update(signInfo);
						json.setSuccess(true);
						json.setMsg("签收成功！！！");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						json.setMsg("签收失败！！！");
						e.printStackTrace();
					}
				}
				
			}else {
				json.setMsg("您只能签收本单位的公文！！！");
			}
			
		}
		return json;
	}
	
	//已签收的公文详情页
	@RequestMapping("/getByID.do")
	public String getByID(Integer id,Model model){
		SignInfo info = signInfoService.selectById(id);
		Document document = info.getDocument();
		if(document!=null){
			List<MyFile> myFiles = document.getMyFiles();
			for (MyFile myFile : myFiles) {
				String myFileName = myFile.getFileName();
				int i =myFileName.indexOf("-");
				myFileName = myFileName.substring(i+1, myFileName.length());
				myFile.setFileName(myFileName);
			}
		}
		model.addAttribute("info", info);
		return "/signInfo/signInfoDetails";
	}
}
