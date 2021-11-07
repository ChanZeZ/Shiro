package com.chan.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {

    @RequestMapping("save")
    //@RequiresRoles(value = {"admin","user"})//注解方式 同时具有
    @RequiresPermissions("user:save:01")//用来判断权限字符串
    public String save(){
        System.out.println("进入方法");
//        //获取主体对象
//        Subject subject = SecurityUtils.getSubject();
//        //代码方式:基于角色
//        if(subject.hasRole("admin")){
//            System.out.println("保存订单");
//        }else {
//            System.out.println("无权访问");
//        }
        return "redirect:/index.jsp";
    }
}
