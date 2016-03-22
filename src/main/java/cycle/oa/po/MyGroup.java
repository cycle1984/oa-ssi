package cycle.oa.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyGroup implements Serializable {
	private static final long serialVersionUID = -3072653428705517043L;

	private Integer id;//主键
	private String name;//类别名称
	private String ownerUnit;
	private String  remark;//备注
	
	private List<Unit> units = new ArrayList<Unit>();
	
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
	public String getOwnerUnit() {
		return ownerUnit;
	}
	public void setOwnerUnit(String ownerUnit) {
		this.ownerUnit = ownerUnit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<Unit> getUnits() {
		return units;
	}
	public void setUnits(List<Unit> units) {
		this.units = units;
	}
	
	
}
