package cycle.oa.po;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户表
 * @author jyj
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 5799322160446271089L;

	private Integer id;//主键
	private Date createdateTime;// 创建日期
	private Date updatedateTime;// 最后修改时间
	private String loginName;// 登陆名
	private String pwd;// 密码
	private String name;// 真实姓名
	private String phone;//手机号码
	private String tel;//办公电话
	private String gender; // 性别
	private String photo;//照片
	private String remark;//备注
	private String dep;//所属科室
	
	private Unit unit;//所属单位
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatedateTime() {
		return createdateTime;
	}
	public void setCreatedateTime(Date createdateTime) {
		this.createdateTime = createdateTime;
	}
	public Date getUpdatedateTime() {
		return updatedateTime;
	}
	public void setUpdatedateTime(Date updatedateTime) {
		this.updatedateTime = updatedateTime;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	//根据struts<s:a>标签的title值判断是否拥有该权限
	/**
	 * 
	 * 判断本用户是否有显示<a>超链接的的权限
	 * 用<a>标签的title和权限里的name对比
	 * @param title
	 * @return
	 */
	public boolean hasMyResourceByTitle(String title) {
		if(isAdmin()){
			return true;
		}
		
		//非超级管理员判断是否拥有此权限
		return false;
	}
	
	
	/**
	 * 判断本用户是否是超级管理员
	 * 
	 * @return
	 */
	public boolean isAdmin() {
		return "admin".equals(loginName);
	}
	
}
