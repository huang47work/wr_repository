package wr.com.shiro;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import wr.com.mapper.RoleMapper;
import wr.com.mapper.UserMapper;
import wr.com.pojo.Role;
import wr.com.pojo.User;

/**
 * @author linaz
 * @created 2016.08.15 17:28
 */
public class UnionShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userRepository;
    @Autowired 
    private RoleMapper roleMapper;
    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     * 本例中该方法的调用时机为需授权资源被访问时
     * 并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * 如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
  /*
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String loginName = (String)super.getAvailablePrincipal(principalCollection);
        User user = userRepository.findByUserName(loginName);
        if(user!=null){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            //用户的角色集合
            List<Map<String, Object>> roleMapList = jdbcTemplate.queryForList("select r.id,r.role_name from user_role ur ,role r where ur.role_id = r.id and user_id = '"+user.getId()+"' ");
            //用户所属机构集合
            List<Map<String, Object>> orgMapList = jdbcTemplate.queryForList("select o.id,o.name from user_org uo,org o where uo.org_id = o.id and uo.user_id = 6 and uo.user_id = '"+user.getId()+"' ");

            //角色权限管理
            List<String> roleIds = new ArrayList<>();
            Set<String> roleNames = new HashSet<>();
            for (Map<String,Object> roleMap : roleMapList){
                roleIds.add(roleMap.get("id").toString());
                roleNames.add(roleMap.get("role_name").toString());
            }

            //机构权限管理
            List<String> orgIds = new ArrayList<>();
            Set<String> orgNames = new HashSet<>();
            for (Map<String,Object> orgMap : orgMapList){
                orgIds.add(orgMap.get("id").toString());
                orgNames.add(orgMap.get("name").toString());
            }

            //合并当前用户角色权限和机构权限
            roleIds.addAll(orgIds);
            roleNames.addAll(orgNames);

            //为当前用户添加角色以及机构权限权限
            simpleAuthorizationInfo.setRoles(roleNames);

            List<String> permissionList = new ArrayList<>();
            if(roleIds!=null && roleIds.size()>0){
                permissionList = permissionRepository.findByRoleIds(roleIds.toArray());
            }

            //为当前用户添加按钮权限
            simpleAuthorizationInfo.addStringPermissions(permissionList);
            return simpleAuthorizationInfo;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }
*/
    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //查出是否有此用户
        System.out.println(token.getUsername());
        User user = userRepository.findByUserNameOrPhone(token.getUsername());
        System.out.println(user);
        if(user!=null){
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        }
        return null;
    }

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.err.println("进入权限验证");
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		String username = (String) principals.getPrimaryPrincipal();
		User user = userRepository.findByUserNameOrPhone(username);
		if (user==null) {
			return null;
		}
		List<Role> list = roleMapper.selectByUserId(user.getUserId());
		Iterator<Role> it = list.iterator();
		Set<String> roleName = new HashSet<String>();
		while (it.hasNext()) {
			Role role = (Role) it.next();
			roleName.add(role.getRoleName());
		}
		System.out.println("权限有："+roleName);
		simpleAuthorizationInfo.addRoles(roleName);
		return simpleAuthorizationInfo;
	}
}