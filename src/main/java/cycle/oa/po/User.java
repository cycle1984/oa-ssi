package cycle.oa.po;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



/**
 * 用户表
 * @author jyj
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 5799322160446271089L;

	private Integer id;//主键
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date createDatetime;// 创建日期
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date updateDatetime;// 最后修改时间
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
	private Role role;//角色
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
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
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	/**
	 * 判断本用户是否是超级管理员
	 * 
	 * @return
	 */
	public boolean isAdmin() {
		return "admin".equals(loginName);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", createdateTime=" + createDatetime + ", updatedateTime=" + updateDatetime
				+ ", loginName=" + loginName + ", pwd=" + pwd + ", name=" + name + ", phone=" + phone + ", tel=" + tel
				+ ", gender=" + gender + ", photo=" + photo + ", remark=" + remark + ", dep=" + dep + ", unit=" + unit
				+ ", role=" + role + "]";
	}
	
	
}
