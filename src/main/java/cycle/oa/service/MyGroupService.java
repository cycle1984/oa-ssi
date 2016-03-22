package cycle.oa.service;

import java.util.List;

import cycle.oa.po.MyGroup;

public interface MyGroupService extends BaseService<MyGroup>{
	
	//查询所有
	public List<MyGroup> findAll(MyGroup group) throws Exception;
	
	//查询总数据量
	public Long findCount() throws Exception;
}
