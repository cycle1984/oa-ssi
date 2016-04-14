package cycle.oa.shiro;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cycle.oa.po.User;
import cycle.oa.service.UserService;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName("myRealm");
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("-----------账号"+token.getPrincipal());//获得用户名
		
		String loginName = (String) token.getPrincipal();
		String loginPassword = new String((char[]) token.getCredentials());
		System.out.println("-----------密码"+loginPassword);//密码
		User entity = new User();
		entity.setLoginName(loginName);
		User user = userService.selectEntity(entity);
		// 如果查询不到返回null
		if(user==null){//
			return null;
		}
		// 从数据库查询到密码
		String password = DigestUtils.md5Hex("admin");
		
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, this.getName()); 
		return simpleAuthenticationInfo;
	}

}
