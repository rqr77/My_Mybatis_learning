package com.dao;

import com.domain.Role;
import com.domain.User;

import java.util.List;

public interface RoleDao {
    /**
     * 查询所有角色
     */
    List<Role> findAll();
    List<User> findAlluser();
}
