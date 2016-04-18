package cycle.oa.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cycle.oa.po.MyResource;
import cycle.oa.po.User;
import cycle.oa.service.MyResourceService;
import cycle.oa.service.UserService;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MyResourceService myResourceService;
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName("myRealm");
	}
	
	// 用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
		//从 principals获取主身份信息
		//将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
		User user = (User) collection.getPrimaryPrincipal();
		//查到权限数据，返回授权信息(要包括 上边的permissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		List<MyResource> myResources = new ArrayList<MyResource>();
		if(user.isAdmin()){//超级管理员
			MyResource entity = new MyResource();
			entity.setType(1);//只查询权限资源
			myResources = myResourceService.selectListByEntity(entity);
		}else{
			myResources = userService.selectMyResourcesByUserId(user.getId());
		}
		for (MyResource myResource : myResources) {
			if(myResource.getPercode()!=null){
				simpleAuthorizationInfo.addStringPermission(myResource.getPercode());
			}
		}
		return simpleAuthorizationInfo;
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
		String password = user.getPwd();
		
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, this.getName()); 
		return simpleAuthenticationInfo;
	}

}
