package cycle.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cycle.oa.mapper.UnitMapper;
import cycle.oa.po.Unit;
import cycle.oa.service.UnitService;

@Service("unitService")
public class UnitServiceImpl implements UnitService {

	@Autowired
	private UnitMapper unitMapper;
	
	@Override
	public List<Unit> findAll(Unit unit) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("unit", unit);
		return unitMapper.selectAll(map);
	}

	@Override
	public Long findCount() throws Exception {
		
		return unitMapper.selectCount();
	}

	@Override
	public void save(Unit t) throws Exception {
		unitMapper.insert(t);
	}

	@Override
	public void delete(Integer[] ids) throws Exception {
		unitMapper.delete(ids);;
	}

	@Override
	public void edit(Unit t) throws Exception {
		unitMapper.update(t);
	}

}
