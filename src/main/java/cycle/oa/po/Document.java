package cycle.oa.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 公文实体类
 * @author jyj
 *
 */
public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5181850655256922166L;
	
	private Integer id ;
	private String docNum;//发文字号
	private String documentTitle;
	private String level;//公文紧急等级
	private Date createDatetime;//创建日期
	private String publishUserName;//发布人姓名
	private String remark;//描述、备注
	private String signInfoString;//用来显示已签收多少单位，未签收多少单位
	
	private Unit publishUnit;//发布人的单位
	private List<SignInfo> signInfos = new ArrayList<SignInfo>(); //签收信息列表
	private List<MyFile> myFiles = new ArrayList<MyFile>();//附件信息(下载所需)
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public String getDocumentTitle() {
		return documentTitle;
	}
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getPublishUserName() {
		return publishUserName;
	}
	public void setPublishUserName(String publishUserName) {
		this.publishUserName = publishUserName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Unit getPublishUnit() {
		return publishUnit;
	}
	public void setPublishUnit(Unit publishUnit) {
		this.publishUnit = publishUnit;
	}
	public List<SignInfo> getSignInfos() {
		return signInfos;
	}
	public void setSignInfos(List<SignInfo> signInfos) {
		this.signInfos = signInfos;
	}
	public List<MyFile> getMyFiles() {
		return myFiles;
	}
	public void setMyFiles(List<MyFile> myFiles) {
		this.myFiles = myFiles;
	}
	public String getSignInfoString() {
		return signInfoString;
	}
	public void setSignInfoString(String signInfoString) {
		this.signInfoString = signInfoString;
	}
	
}
