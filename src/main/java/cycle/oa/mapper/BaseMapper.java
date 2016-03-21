package cycle.oa.mapper;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
	
	//增加
	public void insert(T t) throws Exception;
	
	//批量删除
	public void delete(Integer[] ids) throws Exception;
	
	//修改
	public void update(T t) throws Exception;
	
	//按ID查找
	public T selectById(Integer id) throws Exception;
	
	//按条件查询全部
	public List<T> selectAll(Map<String, Object> map) throws Exception;
	
	public Long selectCount() throws Exception;
}
