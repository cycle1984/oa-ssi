package cycle.oa.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 单位、部门
 * @author jyj
 *
 */
public class Unit implements Serializable {

	private static final long serialVersionUID = -5979357220444776982L;

	private Integer id;//主键
	private Date createDatetime;//创建时间
	private Date updateDatetime;//最后更新时间
	private String name;//单位名称
	private String fullName;//单位全称
	private String tel;//办公室电话
	private String remark;//备注
	private Integer state=0;//状态，0表示正常状态，1表示禁用状态
	
	private MyGroup myGroup;//所属类别
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public MyGroup getMyGroup() {
		return myGroup;
	}
	public void setMyGroup(MyGroup myGroup) {
		this.myGroup = myGroup;
	}
	@Override
	public String toString() {
		return "Unit [id=" + id + ", createdateTime=" + createDatetime + ", updatedateTime=" + updateDatetime
				+ ", name=" + name + ", fullName=" + fullName + ", tel=" + tel + ", remark=" + remark + ", state="
				+ state + ", myGroup=" + myGroup + "]";
	}
	
}
