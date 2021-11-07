package com.chan.shiro.realms;

import com.chan.Utils.ApplicationContextUtils;
import com.chan.entity.Perms;
import com.chan.entity.Role;
import com.chan.entity.User;
import com.chan.service.UserService;
import com.chan.service.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.List;


//自定义Realm
public class UserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("调用授权验证" + primaryPrincipal);
        //根据主身份信息获取角色信息和权限信息
        UserService userServiceImpl = (UserService) ApplicationContextUtils.getbean("userServiceImpl");
        User user = userServiceImpl.findRolesByUserName(primaryPrincipal);
        //授权角色信息
        if(!CollectionUtils.isEmpty(user.getRoles())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role->{
                simpleAuthorizationInfo.addRole(role.getName()); //添加角色信息
                //权限信息
                List<Perms> perms = userServiceImpl.findPermsByRoleId(role.getId());
                System.out.println("perms:"+perms);

                if(!CollectionUtils.isEmpty(perms) && perms.get(0)!=null ){
                    perms.forEach(perm->{
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
//        if("chenzezhong".equals(primaryPrincipal)){
//            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//            simpleAuthorizationInfo.addRole("user");//基于角色的权限管理
//            simpleAuthorizationInfo.addStringPermission("user:find:*");
//            simpleAuthorizationInfo.addStringPermission("user:update:*");
//            simpleAuthorizationInfo.addStringPermission("user:save:02");
//            return simpleAuthorizationInfo;
//        }
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
        System.out.println("User:"+user);
        //用户不为空
        if(!ObjectUtils.isEmpty(user)){
//            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()), this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
