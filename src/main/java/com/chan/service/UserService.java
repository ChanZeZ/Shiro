package com.chan.service;

import com.chan.entity.Perms;
import com.chan.entity.Role;
import com.chan.entity.User;

import java.util.List;

public interface UserService {

    //注册用户
    void register(User user);

    //根据用户名查询业务
    User findByUserName(String username);

    //根据用户名查询所有角色
    User findRolesByUserName(String username);

    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String id);
}
