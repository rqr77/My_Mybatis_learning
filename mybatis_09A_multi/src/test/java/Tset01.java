import com.dao.AccountDao;
import com.dao.UserDao;
import com.domain.Account;
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
    private AccountDao accountDao;



    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new  SqlSessionFactoryBuilder().build(is);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);
        accountDao = sqlSession.getMapper(AccountDao.class);
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
    public void testFindALLAccount(){
        List<Account> list = accountDao.findAll();
        for (Account account : list) {
            System.out.println("------------------------------~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-");
            System.out.println(account);
            System.out.println(account.getUser());
        }

    }
    @Test
    public void testFindALLUserAccount(){
        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println("------------------------------~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }

    }
}
