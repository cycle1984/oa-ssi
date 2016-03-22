package cycle.oa.service;

import java.util.List;

import cycle.oa.po.Unit;

public interface UnitService extends BaseService<Unit>{
	
	//查询所有
	public List<Unit> findAll(Unit unit) throws Exception;
	
	//查询总数据量
	public Long findCount() throws Exception;
}
