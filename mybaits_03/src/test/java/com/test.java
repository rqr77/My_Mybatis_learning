package com;



import com.dao.UserDao;
import com.domain.User;
import com.mybatis.io.Resources;
import com.mybatis.sqlsession.SqlSession;
import com.mybatis.sqlsession.SqlSessionFactory;
import com.mybatis.sqlsession.SqlSessionFactoryBuilder;
import org.apache.log4j.lf5.util.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSourceBuilder =new SqlSessionFactoryBuilder();
        SqlSessionFactory factory =sqlSourceBuilder.build(in);
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        /*System.out.println(userDao);*/
        List<User> list = userDao.findAll();

        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();
        in.close();

    }
}
