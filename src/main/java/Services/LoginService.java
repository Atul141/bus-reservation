package Services;

import Dao.LoginDao;
import Models.UserDetails;
import ServiceImpl.ConfigDB;
import ServiceImpl.LoginImpl;

public class LoginService {

private LoginDao loginDao;
private LoginImpl loginImpl;
private ConfigDB configDB;
public LoginService(ConfigDB configDB){
    this.configDB = configDB;
    loginDao=new LoginDao();
    loginImpl=new LoginImpl(configDB);

}

public boolean validateLogin(UserDetails userDetails){

    loginDao.setPassword(userDetails.getPassword());
    loginDao.setEmail(userDetails.getEmail());
   return loginImpl.validateLogin(loginDao);

}
}
