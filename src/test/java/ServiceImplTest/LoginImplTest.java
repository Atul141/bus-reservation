package ServiceImplTest;

import Dao.LoginDao;
import Dao.UserDetailsDao;
import Database.ConfigTest;
import ServiceImpl.LoginImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginImplTest {

    private Session session = null;
    private LoginImpl login;
    private UserDetailsDao userDetailsDao;

    @Before
    public void before() {
        login = new LoginImpl();
        ConfigTest configTest = new ConfigTest();
        session = configTest.getTestSession();
        userDetailsDao = configTest.getUserDetailsinstance();
        Transaction transaction = session.beginTransaction();
        session.persist(userDetailsDao);
        session.flush();
        transaction.commit();
    }

    @Test
    public void shouldValidateLoginIfCredentialsAreCorrect() {
        LoginDao loginDao = new LoginDao();
        loginDao.setEmail(userDetailsDao.getEmail());
        loginDao.setPassword(userDetailsDao.getPassword());
        assertTrue(login.validateLogin(loginDao));
    }
    @Test
    public void shouldValidateLoginIfCredentialsAreInCorrect() {
        LoginDao loginDao = new LoginDao();
        loginDao.setEmail("sample@gmail.com ");
        loginDao.setPassword("password");
        assertFalse(login.validateLogin(loginDao));
    }

    @After
    public void delete(){
        Transaction delete = session.beginTransaction();
        session.delete(userDetailsDao);
        delete.commit();
        session.close();
    }
}
