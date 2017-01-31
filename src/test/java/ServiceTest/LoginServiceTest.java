package ServiceTest;


import Dao.LoginDao;
import Models.UserDetails;
import ServiceImpl.LoginImpl;
import Services.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    private LoginDao loginDao;
    private LoginImpl login;
    private LoginService loginService;

    @Before
    public void setup() {
        initMocks(this);
        loginDao = new LoginDao();
        login = mock(LoginImpl.class);
        loginService = new LoginService();
    }


    @Test
    public void shouldCallValidateMethodOfLoginImpl() {

        UserDetails userDetails = new UserDetails();
        userDetails.setEmail("abc@gmail.com");
        userDetails.setPassword("pass");
        loginDao.setEmail("abc@gmail.com");
        loginDao.setPassword("pass");
        when(login.validateLogin(loginDao)).thenReturn(false);
        assertTrue(loginService.validateLogin(userDetails));

    }

}
