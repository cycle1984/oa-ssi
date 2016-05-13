package cycle.oa.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * model数据
 * @author cycle
 *
 * @param <T>
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 6026241623576800774L;
	
	private Integer page ;//当前页
	private Integer rows;//页大小，每页显示几条数据
	private Integer start;//数据起始下标序号，需要这里处理
	private Long totalRecord = 0L;//总记录 数
	private List<T> list = new ArrayList<T>();//页面数据列表
	private T paramEntity;//多条件查询
	private String sort;// 排序字段
	private String order = "asc";// asc/desc
	private Map<String, Object> pageMap = new HashMap<String, Object>() ;//可用于向后台传json数据的map
	
	private Integer docId;//公文id
	
	//用于工具栏查询的起始和结束时间的参数传递
	private Date createDatetime_start;
	private Date createDatetime_end =  new Date();
	
	//当期日期的一年前的时间点，一年前的公文归到历史公文
	private Date oneYearAgo;
	//用于判断查询条件，true查询1年前的公文，false查询1年内的公文
	private Boolean history;
	
	//构造函数，初始化
	public Page() {
		// TODO Auto-generated constructor stub
		Calendar calendar = Calendar.getInstance();
		//设置当期日期往前一年，时分秒为0
		calendar.add(Calendar.YEAR, -1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		this.oneYearAgo = calendar.getTime();
	}
	
	public Date getOneYearAgo() {
		
		return oneYearAgo;
	}
	public void setOneYearAgo(Date oneYearAgo) {
		this.oneYearAgo = oneYearAgo;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getStart() {
		this.start = (page-1)*rows;//获得数据起始下标号
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Long totalRecord) {
		pageMap.put("total", totalRecord);//将查询出来的总记录数放入map
		this.totalRecord = totalRecord;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		pageMap.put("rows", list);//将查询出来的记录list放入map
		this.list = list;
	}
	public T getParamEntity() {
		return paramEntity;
	}
	public void setParamEntity(T paramEntity) {
		this.paramEntity = paramEntity;
	}
	public Map<String, Object> getPageMap() {
		return pageMap;
	}
	public void setPageMap(Map<String, Object> pageMap) {
		this.pageMap = pageMap;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public Date getCreateDatetime_start() {
		return createDatetime_start;
	}
	public void setCreateDatetime_start(Date createDatetime_start) {
		this.createDatetime_start = createDatetime_start;
	}
	public Date getCreateDatetime_end() {
		return createDatetime_end;
	}
	public void setCreateDatetime_end(Date createDatetime_end) {
		//因为传入的日期是当天的00:00:00.0，所以终止日期应该+1天
		Calendar cal=Calendar.getInstance();
		cal.setTime(createDatetime_end);
		cal.add(Calendar.DATE, 1);
		this.createDatetime_end = cal.getTime();
	}
	
	public Boolean getHistory() {
		return history;
	}
	public void setHistory(Boolean history) {
		this.history = history;
	}
	@Override
	public String toString() {
		return "Page [page=" + page + ", rows=" + rows + ", start=" + start + ", totalRecord=" + totalRecord + ", list="
				+ list + ", paramEntity=" + paramEntity + ", sort=" + sort + ", order=" + order + ", pageMap=" + pageMap
				+ "]";
	}
	
	
}
