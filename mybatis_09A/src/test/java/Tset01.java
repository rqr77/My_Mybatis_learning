import com.dao.UserDao;
import com.domain.User;
import com.mysql.cj.xdevapi.SessionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Tset01 {
    private UserDao userDao;
    private InputStream is;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;



    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new  SqlSessionFactoryBuilder().build(is);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);
    }
    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        is.close();

    }

    @Test
    public void testFindALL(){
        List<User> list = userDao.findAll();
        System.out.println(list);
    }
    @Test
    public void testSave(){
        User user =new User();
        user.setId(233);
        user.setName("pig");
        userDao.saveUser(user);
        List<User> list = userDao.findAll();
        System.out.println(list);
    }
    @Test
    public void testUpdate(){
        User user =new User();
        user.setId(1);
        user.setUsername("rqr");
        userDao.updateUser(user);
        List<User> list = userDao.findAll();
        System.out.println(list);
    }

    @Test
    public void testDel(){
        userDao.delUser(233);
    }
    @Test
    public void testFindById(){
        User user = userDao.findById(18);
        System.out.println(user);
    }
    @Test
    public void testFindByName(){
        List<User> list = userDao.findByName("%魔%");
        System.out.println(list);
    }

    @Test
    public void testFindTotal(){
        int count = userDao.findTotal();
        System.out.println(count);
    }

}
