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
	public List<MyGroup> findAll() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		return myGroupMapper.selectAll(map);
	}

	@Override
	public Long findCount() throws Exception {
		
		return myGroupMapper.selectCount();
	}

}
