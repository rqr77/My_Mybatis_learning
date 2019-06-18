package com.dao;
import com.domain.QuerySelect;
import com.domain.User;
import java.util.List;

public interface UserDao {
    List<User> findAll();
    void saveUser(User user);
    void updateUser(User user);
    void delUser(String id);
    User findById(String id);
    List<User> findByName(String name);
    List<User> findByQS(QuerySelect querySelect);
    List<User> findByCondition(User user);
    List<User> findByUserInIds(QuerySelect querySelect);
}
