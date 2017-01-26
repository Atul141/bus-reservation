package Services;

import Dao.UserDetailsDao;
import Models.UserDetails;
import ServiceImpl.UserDetailsImpl;

public class UserDetailsService {


    private UserDetailsImpl userDetailsImpl;
    private UserDetailsDao userDetailsDao;
    public UserDetailsService(){
        userDetailsDao=new UserDetailsDao();
        userDetailsImpl=new UserDetailsImpl();
    }

    public void saveUserDetails(UserDetails userDetails){
        userDetailsDao.setPassword(userDetails.getPassword());
        userDetailsDao.setLastName(userDetails.getLastName());
        userDetailsDao.setEmail(userDetails.getEmail());
        userDetailsDao.setFirstName(userDetails.getFirstName());
        userDetailsDao.setPhone(userDetails.getPhone());
        userDetailsImpl.saveToDb(userDetailsDao);

    }
}