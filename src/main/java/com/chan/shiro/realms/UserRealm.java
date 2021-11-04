package com.chan.shiro.realms;

import com.chan.Utils.ApplicationContextUtils;
import com.chan.entity.User;
import com.chan.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

import java.security.Principal;


//自定义Realm
public class UserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("调用授权验证" + primaryPrincipal);
        //根据主身份信息获取角色信息和权限信息
        if("chenzezhong".equals(primaryPrincipal)){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole("user");//基于角色的权限管理
            simpleAuthorizationInfo.addStringPermission("user:find:*");
            simpleAuthorizationInfo.addStringPermission("user:update:*");
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("====================");

        //根据身份信息
        System.out.println("成功登录！");
        String principal = (String) authenticationToken.getPrincipal();
        //在工厂中获取service对象
        UserService userServiceImpl = (UserService) ApplicationContextUtils.getbean("userServiceImpl");
        User user = userServiceImpl.findByUserName(principal);
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
        }
        return null;
    }
}
