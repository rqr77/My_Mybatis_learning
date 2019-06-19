package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {
    /**
     *
     * @return
     */
    @Select("select * from userinf")
    List<User> findAll();

    @Insert("insert into userinf(id, name,gender,age,address,qq,email,username,password) values(#{id},#{name},#{gender},#{age},#{address},#{qq},#{email},#{username},#{password})")
    void saveUser(User user);

    @Update("update userinf set username= #{username} where id=#{id}")
    void updateUser(User user);

    @Delete("delete from userinf where id = #{id}")
    void delUser(int id);

    @Select("select * from userinf where id=#{id}")
    User findById(int id);

    @Select("select * from userinf where name like #{name}")
    List<User> findByName(String name);

    @Select("select count(*) from userinf ")
    int findTotal();


}
