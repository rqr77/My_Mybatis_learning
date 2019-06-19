package com.dao;

import com.domain.Account;
import com.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountDao {
    @Select("select * from account ")
    @Results(id ="accountuserMap",value = {
            @Result(id = true,column ="id", property = "id"),
            @Result(column ="uid", property = "uid"),
            @Result(column ="money", property = "money"),
            @Result(property = "user",column = "uid" , one=@One(
                    select="com.dao.UserDao.findById",fetchType = FetchType.LAZY) )

    })
    List<Account> findAll();


    @Select("select * from account where uid=#{uid}")
    Account findByUid(int uid);

}
