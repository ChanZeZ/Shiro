package com.chan.dao;

import com.chan.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {

    void save(User user);
    User findByUserName(String username);

}
