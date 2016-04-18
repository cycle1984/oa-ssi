package cycle.oa.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 * @author jyj
 *
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 818605821067783419L;
	
	private Integer id;//主键
	private String name;//角色名称
	private String remark;//备注
	
	private List<User> users = new ArrayList<User>();//含有的用户
	private List<MyResource> myResources = new ArrayList<MyResource>();//所含有的权限
	
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<MyResource> getMyResources() {
		return myResources;
	}
	public void setMyResources(List<MyResource> myResources) {
		this.myResources = myResources;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", remark=" + remark + ", users=" + users + ", myResources="
				+ myResources + "]";
	}
	
}
