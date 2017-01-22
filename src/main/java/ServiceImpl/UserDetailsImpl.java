package ServiceImpl;

import Dao.UserDetailsDao;
import Models.UserDetails;

public class UserDetailsImpl {

    private UserDetails userDetails;
    private UserDetailsDao userDetailsDao;

    public UserDetailsImpl(UserDetails userDetails){

        this.userDetails = userDetails;
    }


}
