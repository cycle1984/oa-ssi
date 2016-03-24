package cycle.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cycle.oa.mapper.UserMapper;
import cycle.oa.po.Page;
import cycle.oa.po.User;
import cycle.oa.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void save(User t) throws Exception {
		userMapper.insert(t);
	}

	@Override
	public void deleteByArray(Integer[] ids) throws Exception {
		userMapper.deleteByArray(ids);
	}

	@Override
	public void edit(User t) throws Exception {
		userMapper.update(t);
	}

	@Override
	public Page<User> selectPageDyc(Page<User> page) {
		page.setList(userMapper.selectPageListDyc(page));//将数据封装
		page.setTotalRecord(userMapper.selectPageCountDyc(page));//将总记录数封装
		return page;
	}

	@Override
	public User selectById(Integer id) {
		return userMapper.selectById(id);
	}

}
