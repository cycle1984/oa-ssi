package cycle.oa.mapper;

import java.util.List;
import java.util.Map;

import cycle.oa.po.Page;

public interface BaseMapper<T> {
	
	//增加
	public void insert(T t) throws Exception;
	
	//根据ID主键批量删除(数组)
	public void deleteByArray(Integer[] ids) throws Exception;
	
	//根据ID主键批量删除(list)
	public void deleteByList(Integer[] id) throws Exception;
	
	//修改
	public void update(T t) throws Exception;
	
	//按ID查找
	public T selectById(Integer id);
	
	//按条件查询全部
	public List<T> selectAll(Map<String, Object> map) throws Exception;
	
	
	public Long selectCount() throws Exception;
	
	//通过关键字分页查询数据列表
	public List<T> selectPageListDyc(Page<T> page);
	
	//通过关键字分页查询，返回总记录数
	public Long selectPageCountDyc(Page<T> page);
}
