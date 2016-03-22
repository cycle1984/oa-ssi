package cycle.oa.service;

public interface BaseService<T> {

	public void save(T t) throws Exception;
	
	public void delete(Integer[] ids) throws Exception;
	
	public void edit(T t) throws Exception;
}
