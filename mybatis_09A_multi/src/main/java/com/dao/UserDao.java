package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserDao {
    /**
     *
     * @return
     */
    @Select("select * from userinf")
    @Results( id= "usermap",value={
            @Result(id =true,column ="id",property ="userid"),
            @Result(column ="name",property ="name"),
            @Result(property="gender", column="gender"),
            @Result(property="age",column="age"),
            @Result(property="address",column="address"),
            @Result(property="qq",column="qq"),
            @Result(property="email",column="email"),
            @Result(property="username",column="username"),
            @Result(property="password",column="password"),
            @Result(property = "accounts",column = "id",many = @Many(
                    select = "com.dao.AccountDao.findByUid",fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    @Select("select * from userinf where id=#{id}")
    @ResultMap("usermap")
    User findById(int id);

    @Select("select * from userinf where name like %${name}%")
    List<User> findByName(String name);


}
