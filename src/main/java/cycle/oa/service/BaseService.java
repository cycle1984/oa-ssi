package cycle.oa.service;


import java.util.List;

import cycle.oa.po.Page;

public interface BaseService<T> {

	public void save(T t) throws Exception;
	
	public void deleteByArray(Integer[] ids) throws Exception;
	
	public void edit(T t) throws Exception;
	
	public T selectById (Integer id);
	
	//查询单个对象
	public T selectEntity(T entity);
	
	//查询多个对象
	public List<T> selectListByEntity(T entity);
	
	//通过多条件分页查询
	public Page<T> selectPageDyc(Page<T> page);
}
