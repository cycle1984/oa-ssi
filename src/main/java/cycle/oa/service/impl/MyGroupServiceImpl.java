package cycle.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cycle.oa.mapper.MyGroupMapper;
import cycle.oa.po.MyGroup;
import cycle.oa.service.MyGroupService;

@Service("myGroupService")
public class MyGroupServiceImpl implements MyGroupService {

	@Autowired
	private MyGroupMapper myGroupMapper;
	
	@Override
	public List<MyGroup> findAll(MyGroup group) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("----------"+group.getName());
		map.put("group", group);
		return myGroupMapper.selectAll(map);
	}

	@Override
	public Long findCount() throws Exception {
		
		return myGroupMapper.selectCount();
	}

	@Override
	public void save(MyGroup t) throws Exception {
		myGroupMapper.insert(t);
	}

	@Override
	public void delete(Integer[] ids) throws Exception {
		myGroupMapper.delete(ids);;
	}

	@Override
	public void edit(MyGroup t) throws Exception {
		myGroupMapper.update(t);
	}

}
