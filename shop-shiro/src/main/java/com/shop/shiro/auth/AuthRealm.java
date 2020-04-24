package com.shop.shiro.auth;

import com.shop.manage.entity.ZsAdmin;
import com.shop.manage.entity.ZsAuthority;
import com.shop.manage.entity.ZsRole;
import com.shop.manage.service.impl.ZsAdminServiceImpl;
import com.shop.manage.service.impl.ZsAuthorityServiceImpl;
import com.shop.manage.service.impl.ZsRoleServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author CrazyJay
 * @Date 2019/3/30 21:38
 * @Version 1.0
 */
@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private ZsAdminServiceImpl zsAdminService;

    @Autowired
    private ZsRoleServiceImpl zsRoleService;

    @Autowired
    private ZsAuthorityServiceImpl zsAuthorityService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    /**
     * 授权(验证权限时候调用
     *@param  [principals]
     *@return org.apache.shiro.authz.AuthorizationInfo
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        ZsAdmin user = (ZsAdmin) principals.getPrimaryPrincipal();
        //2.添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<ZsRole> userRoles = zsRoleService.getRolesByAdmin(user.getId());
        for (ZsRole role : userRoles) {
            //2.1添加角色
            simpleAuthorizationInfo.addRole(role.getName());
            for (ZsAuthority permission : zsAuthorityService.getAuthoritiesByRole(role.getId())) {
                //2.1.1添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getApi());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    /**
     * 认证(登陆时候调用)
     *@param  [token]
     *@return org.apache.shiro.authc.AuthenticationInfo
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        //1. 根据accessToken，查询用户信息(这里换成redis缓存的方式)
        String adminId = redisTemplate.opsForValue().get(accessToken);
        //2. token失效
        if(adminId == null || adminId.equals("")) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        ZsAdmin user = zsAdminService.findByAdminId(adminId);
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, this.getName());
        return info;
    }
}
