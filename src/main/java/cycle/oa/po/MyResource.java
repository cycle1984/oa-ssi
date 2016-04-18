package cycle.oa.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyResource implements Serializable {

	private static final long serialVersionUID = -5173991730766957563L;

	private Integer id;
	private String name;//名称
	private String url;//链接
	private String percode;//权限
	private String remark;//描述 
	private String iconCls;//图标
	private Integer seq;//顺序
	private String target;//目标
	private Integer type = 0;//0表示只在左侧菜单显示，1表示功能、权限资源
	
	
	private MyResource paretResource;//上级权限
	private List<MyResource> myResources = new ArrayList<MyResource>();//拥有的下级权限
	private List<Role> roles = new ArrayList<Role>();//拥有此权限的角色
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getPercode() {
		return percode;
	}
	public void setPercode(String percode) {
		this.percode = percode;
	}
	public MyResource getParetResource() {
		return paretResource;
	}
	public void setParetResource(MyResource paretResource) {
		this.paretResource = paretResource;
	}
	public List<MyResource> getMyResources() {
		return myResources;
	}
	public void setMyResources(List<MyResource> myResources) {
		this.myResources = myResources;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
