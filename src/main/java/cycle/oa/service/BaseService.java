package cycle.oa.service;


import java.util.List;
import java.util.Map;

import cycle.oa.po.Page;

public interface BaseService<T> {

	public void save(T t) throws Exception;
	
	public void deleteByArray(Integer[] ids) throws Exception;
	
	public void update(T t) throws Exception;
	
	public T selectById (Integer id);
	
	public List<T> selectByIds(Integer[] ids);
	
	//查询单个对象
	public T selectEntity(T entity);
	
	public List<T> selectAll(Map<String,Object> map);
	
	//查询多个对象
	public List<T> selectListByEntity(T entity);
	
	//通过多条件分页查询
	public Page<T> selectPageDyc(Page<T> page);
}
