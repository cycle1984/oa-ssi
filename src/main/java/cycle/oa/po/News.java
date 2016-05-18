package cycle.oa.po;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6596994031960630877L;
	private Integer id;
	private String title;//标题
	private String content;//内容
	private Date createTime;//创建时间
	private Date updateTime;
	private String author;//作者
	
	private Unit unit;//发布单位

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
}
