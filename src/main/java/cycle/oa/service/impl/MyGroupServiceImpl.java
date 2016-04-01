package cycle.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cycle.oa.po.MyGroup;
import cycle.oa.service.MyGroupService;

@Service("myGroupService")
public class MyGroupServiceImpl extends BaseServiceImpl<MyGroup> implements MyGroupService {

	

	@Override
	public List<MyGroup> findAll(MyGroup group) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		return myGroupMapper.selectAll(map);
	}

	
//	@Override
//	public List<MyGroup> findAll(MyGroup group) throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("group", group);
//		return myGroupMapper.selectAll(map);
//	}
//
//	@Override
//	public Long findCount() throws Exception {
//		
//		return myGroupMapper.selectCount();
//	}
//
//	@Override
//	public void save(MyGroup t) throws Exception {
//		myGroupMapper.insert(t);
//	}
//
//	@Override
//	public void deleteByArray(Integer[] ids) throws Exception {
//		myGroupMapper.deleteByArray(ids);
//	}
//
//	@Override
//	public void update(MyGroup t) throws Exception {
//		myGroupMapper.update(t);
//	}
//
//	
//	@Override
//	public Page<MyGroup> selectPageDyc(Page<MyGroup> page) {
//		page.setList(myGroupMapper.selectPageListDyc(page));//将数据封装
//		page.setTotalRecord(myGroupMapper.selectPageCountDyc(page));//将总记录数封装
//		return page;
//	}
//
//	@Override
//	public MyGroup selectById(Integer id) {
//		// TODO Auto-generated method stub
//		return myGroupMapper.selectById(id);
//	}
//
//	@Override
//	public List<MyGroup> selectListByEntity(MyGroup entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public MyGroup selectEntity(MyGroup entity) {
//		// TODO Auto-generated method stub
//		return myGroupMapper.selectEntity(entity);
//	}

}
