package com.dao;

import com.domain.User;
import com.mybatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("select * from user")
    List<User> findAll();

}
