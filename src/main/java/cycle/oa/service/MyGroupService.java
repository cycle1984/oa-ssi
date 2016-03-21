package cycle.oa.service;

import java.util.List;

import cycle.oa.po.MyGroup;

public interface MyGroupService {
	
	//查询所有
	public List<MyGroup> findAll() throws Exception;
	
	//查询总数据量
	public Long findCount() throws Exception;
}
