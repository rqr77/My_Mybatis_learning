package Mytest;

import com.dao.AccountDao;
import com.dao.UserDao;
import com.domain.Account;
import com.domain.AccountUser;
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
import java.util.ArrayList;
import java.util.List;

public class test {
    private InputStream is;
    private SqlSession sqlSession;
    private UserDao userDao;
    private AccountDao accountDao;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
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
    public void TestFindAllAccount(){
        List<Account> list = accountDao.findAll();
        System.out.println(list);


    }
    @Test
    public void TestFindAllAccount_User(){
        List<AccountUser> list = accountDao.findAllAccount();
        System.out.println(list);


    }
    @Test
    public void TestFindAll_User(){
        List<Account> list = accountDao.findAll();
        for (Account account : list) {
            System.out.println("--------------------------------------------");
            System.out.println(account);
            System.out.println(account.getUser());
        }



    }


}
