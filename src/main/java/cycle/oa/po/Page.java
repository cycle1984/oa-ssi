package cycle.oa.po;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<T> implements Serializable {

	private static final long serialVersionUID = 6026241623576800774L;
	
	private Integer page ;//当前页
	private Integer rows;//页大小，每页显示几条数据
	private Integer start;//数据起始下标序号，需要这里处理
	private Long totalRecord = 0L;//总记录 数
	private List<T> list;//页面数据列表
	private T paramEntity;//多条件查询
	private String sort;// 排序字段
	private String order = "asc";// asc/desc
	private Map<String, Object> pageMap = new HashMap<String, Object>() ;//可用于向后台传json数据的map
	
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
	@Override
	public String toString() {
		return "Page [page=" + page + ", rows=" + rows + ", start=" + start + ", totalRecord=" + totalRecord + ", list="
				+ list + ", paramEntity=" + paramEntity + ", sort=" + sort + ", order=" + order + ", pageMap=" + pageMap
				+ "]";
	}
	
	
}
