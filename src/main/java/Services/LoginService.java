package Services;

import Dao.LoginDao;
import Dao.UserDetailsDao;
import Models.UserDetails;
import ServiceImpl.LoginImpl;

public class LoginService {

private LoginDao loginDao;
private LoginImpl loginImpl;

public LoginService(){
    loginDao=new LoginDao();
    loginImpl=new LoginImpl();
}

public boolean validateLogin(UserDetails userDetails){

    loginDao.setPassword(userDetails.getPassword());
    loginDao.setEmail(userDetails.getEmail());
   return loginImpl.validateLogin(loginDao);

}
}
