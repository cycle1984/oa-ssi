package cycle.oa.service;


import cycle.oa.po.Page;

public interface BaseService<T> {

	public void save(T t) throws Exception;
	
	public void delete(Integer[] ids) throws Exception;
	
	public void edit(T t) throws Exception;
	
	public T selectById (Integer id);
	//通过多条件分页查询
	public Page<T> selectPageDyc(Page<T> page);
}
