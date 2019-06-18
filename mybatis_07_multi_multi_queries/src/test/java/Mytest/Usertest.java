package Mytest;

import com.dao.RoleDao;
import com.dao.UserDao;
import com.domain.Role;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.naming.ldap.Rdn;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Usertest {
    private InputStream is;
    private SqlSession sqlSession;
    private UserDao userDao;
    private RoleDao roleDao;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);
        roleDao = sqlSession.getMapper(RoleDao.class);
    }
    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        is.close();
    }

    @Test
        public void TestAll(){
            List<User> list = userDao.findAll();
            for (User user : list) {
                System.out.println(user);
            }
    }

    @Test
    public void TestFindById(){
        User user = userDao.findById("0001");
        System.out.println(user);
    }
        @Test
        public void TestFindAll_User1(){
            List<User> userList = userDao.findAll();
            for (User user : userList) {
                System.out.println("-------------------------------------------");
                System.out.println(user);
            }


    }
    @Test
/*    public void TestFindAllRole(){
        List<Role> RoleList = roleDao.findAll();
        for (Role role : RoleList) {
            System.out.println("-------------------------------------------");
            System.out.println(role);
            System.out.println(role.getList());
        }


    }*/

    public void TestFindAllUser(){
        List<User> RoleList = roleDao.findAlluser();
        for (User user : RoleList) {
            System.out.println("-------------------------------------------");
            System.out.println(user);
            System.out.println(user.getRoles());
        }


    }

}
