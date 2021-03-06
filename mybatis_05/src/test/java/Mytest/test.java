package Mytest;

import com.dao.UserDao;
import com.dao.daoImpl.UserDaoImpl;
import com.domain.QuerySelect;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class test {
    private InputStream is;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(is);
        /*sqlSession = sqlSessionFactory.openSession();*/
        userDao = new UserDaoImpl(sqlSessionFactory);

    }
    @After
    public void destroy() throws IOException {
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
    public void TestSave() {
        User user = new User();
        user.setId("0011");
        user.setUsername("哈呵呵");
        user.setPassword("1212234");
        userDao.saveUser(user);
        List<User> list = userDao.findAll();
        for (User u : list) {
            System.out.println(u);
        }
    }
    @Test
    public void TestUpdate() {
        User user = new User();
        user.setId("0009");
        user.setUsername("呵呵哈哈哈哈");
        user.setPassword("112234");
        userDao.updateUser(user);
    }
    @Test
    public void TestDel() {
        String s = "0009";
        userDao.delUser(s);
    }
    @Test
    public void TestFindById(){
        User user = userDao.findById("0001");
        System.out.println(user);
    }
    @Test
    public void TestFindByQS(){
        QuerySelect querySelect =new QuerySelect();
        User user =new User();
        user.setUsername("%r%");
        querySelect.setUser(user);
        List<User> list = userDao.findByQS(querySelect);

        System.out.println(list);
    }
}
