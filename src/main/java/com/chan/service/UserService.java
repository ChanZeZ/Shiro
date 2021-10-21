package com.chan.service;

import com.chan.entity.User;

public interface UserService {
    //注册用户
    void register(User user);
    //根据用户名查询业务
    User findByUserName(String username);
}
