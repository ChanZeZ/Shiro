package com.chan.config;

import com.chan.shiro.realms.UserRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来整合Shiro框架相关的配置类
 * @author 泽众
 */
@Configuration
public class ShiroConfig {
    //1.创建shiroFilter,负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置受限资源
        Map<String,String> map = new HashMap<String,String>();
        map.put("/user/login","anon");
        map.put("/user/register","anon");
        map.put("/register.jsp","anon");
        map.put("/**","authc");//authc 代表请求这个资源需要认证和授权
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //配置公共资源
        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");

        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;

    }

    //3.创建自定义realm
    @Bean
    public Realm getRealm(){
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }
}
