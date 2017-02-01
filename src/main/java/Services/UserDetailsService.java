package Services;

import Dao.UserDetailsDao;
import Models.UserDetails;
import ServiceImpl.ConfigDB;
import ServiceImpl.SequenceGenerator;
import ServiceImpl.UserDetailsImpl;

public class UserDetailsService {


    private UserDetailsImpl userDetailsImpl;
    private UserDetailsDao userDetailsDao;
    private SequenceGenerator sequenceGenerator;

    public UserDetailsService(ConfigDB configDB) {
        userDetailsDao = new UserDetailsDao();
        userDetailsImpl = new UserDetailsImpl(configDB);
        sequenceGenerator = new SequenceGenerator();
    }

    public void saveUserDetails(UserDetails userDetails) {
        userDetailsDao.setPassword(userDetails.getPassword());
        userDetailsDao.setLastName(userDetails.getLastName());
        userDetailsDao.setEmail(userDetails.getEmail());
        userDetailsDao.setFirstName(userDetails.getFirstName());
        userDetailsDao.setPhone(userDetails.getPhone());
        userDetailsDao.setId(sequenceGenerator.generateSequenceUserDetails());

        userDetailsImpl.saveToDb(userDetailsDao);


    }
}
