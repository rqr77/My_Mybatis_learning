package com.dao.daoImpl;

import com.dao.UserDao;
import com.domain.QuerySelect;
import com.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<User> findAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users  = sqlSession.selectList("com.dao.UserDao.findAll");
        sqlSession.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("com.dao.UserDao.saveUser",user);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public void updateUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.update("com.dao.UserDao.updateUser",user);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public void delUser(String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("com.dao.UserDao.delUser",id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public User findById(String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("com.dao.UserDao.findById", id);
        sqlSession.close();
        return user;
    }

    @Override
    public List<User> findByQS(QuerySelect querySelect) {
        return null;
    }

}
