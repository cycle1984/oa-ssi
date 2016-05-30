package cycle.oa.po;

import java.io.Serializable;

public class SysBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3200524342255939578L;
	private Integer id ;
	private String path;//公文存储路径
	private Integer refleshTime;//刷新时间,单位分钟
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getRefleshTime() {
		return refleshTime;
	}
	public void setRefleshTime(Integer refleshTime) {
		this.refleshTime = refleshTime;
	}
	
	public SysBase() {
	}
	
	public SysBase(String path, Integer refleshTime) {
		this.path = path;
		this.refleshTime = refleshTime;
	}
	
}
