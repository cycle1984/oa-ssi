package cycle.oa.service;

import java.util.List;

import cycle.oa.po.MyResource;
import cycle.oa.po.User;

public interface UserService extends BaseService<User>{
	public List<MyResource> selectMyResourcesByUserId(Integer id);
}
