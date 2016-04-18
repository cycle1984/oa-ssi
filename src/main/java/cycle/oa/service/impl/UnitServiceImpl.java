package cycle.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cycle.oa.mapper.UnitMapper;
import cycle.oa.po.Page;
import cycle.oa.po.Unit;
import cycle.oa.service.UnitService;

@Service("unitService")
public class UnitServiceImpl extends BaseServiceImpl<Unit> implements UnitService {

	@Autowired
	private UnitMapper unitMapper;
	
	@Override
	public List<Unit> findAll(Unit unit) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
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
	public void deleteByArray(Integer[] ids) throws Exception {
		unitMapper.deleteByArray(ids);
	}

	@Override
	public void update(Unit t) throws Exception {
		unitMapper.update(t);
	}

	@Override
	public Page<Unit> selectPageDyc(Page<Unit> page) {
		page.setList(unitMapper.selectPageListDyc(page));//将数据封装
		page.setTotalRecord(unitMapper.selectPageCountDyc(page));//将总记录数封装
		return page;
	}

	@Override
	public Unit selectById(Integer id) {
		// TODO Auto-generated method stub
		return unitMapper.selectById(id);
	}

	@Override
	public List<Unit> selectListByEntity(Unit entity) {
		return unitMapper.selectListByEntity(entity);
	}

	@Override
	public Unit selectEntity(Unit entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
