package ServiceImplTest;

import Dao.LoginDao;
import Dao.UserDetailsDao;
import ServiceImpl.*;
import ServiceImpl.SaveImpl;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginImplTest {

    private Session session = null;
    private LoginImpl login;
    private UserDetailsDao userDetailsDao;
    private ConfigDB configDB;
    private ConfigTest configTest;

    @Before
    public void before() {
        configTest = new ConfigTest();
        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        login = new LoginImpl(configDB);
        session = configDB.getSession();
        SaveImpl saveImpl = new SaveImpl(configDB);
        userDetailsDao = configTest.getUserDetailsinstance();
        saveImpl.saveToDb(userDetailsDao);
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
    public void delete() {
        configTest.delete(userDetailsDao);
    }
}
