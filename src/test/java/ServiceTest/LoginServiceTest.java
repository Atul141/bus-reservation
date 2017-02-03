package ServiceTest;


import Dao.LoginDao;
import Dao.UserDetailsDao;
import Models.UserDetails;
import ServiceImpl.ConfigDB;
import ServiceImpl.SaveImpl;
import ServiceImpl.SyntaxSugar;
import ServiceImplTest.ConfigTest;
import Services.LoginService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    private LoginService loginService;
    private ConfigDB configDB;
    private ConfigTest configTest;
    private UserDetailsDao userDetailsDao;

    @Before
    public void setup() {
        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        configTest = new ConfigTest();
        loginService = new LoginService(configDB);
        SaveImpl saveImpl = new SaveImpl(configDB);
        userDetailsDao = configTest.getUserDetailsinstance();
        saveImpl.saveToDb(userDetailsDao);
    }


    @Test
    public void shouldCallValidateMethodOfLoginImpl() {

        UserDetails userDetails = new UserDetails();
        userDetails.setEmail("test5@gmail.com");
        userDetails.setPassword("pass");
        LoginDao loginDao = new LoginDao();
        loginDao.setPassword("pass");
        loginDao.setEmail("test5@gmail.com");
        assertTrue(loginService.validateLogin(userDetails));

    }


    @After
    public void delete() {
        configTest.delete(userDetailsDao);
    }

}
