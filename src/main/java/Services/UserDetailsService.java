package Services;

import Dao.UserDetailsDao;
import Models.UserDetails;
import ServiceImpl.*;

import java.io.IOException;
import java.net.Socket;

public class UserDetailsService {


    private UserDetailsImpl userDetailsImpl;
    private SequenceGenerator sequenceGenerator;

    public UserDetailsService(ConfigDB configDB) {
        userDetailsImpl = new UserDetailsImpl(configDB);
        sequenceGenerator = new SequenceGenerator(configDB);
    }

    public void saveUserDetails(UserDetails userDetails) {
        UserDetailsDao userDetailsDao = mapToUserDetailsDao(userDetails);
        System.out.println(userDetails.getFirstName());
        System.out.println(userDetails.getLastName());
        userDetailsDao.setId(sequenceGenerator.generateSequenceUserDetails());
        userDetailsImpl.saveToDb(userDetailsDao);

    }

    private UserDetailsDao mapToUserDetailsDao(UserDetails userDetails) {
        UserDetailsDao userDetailsDao = new UserDetailsDao();
        userDetailsDao.setPassword(userDetails.getPassword());
        userDetailsDao.setLastName(userDetails.getLastName());
        userDetailsDao.setEmail(userDetails.getEmail());
        userDetailsDao.setFirstName(userDetails.getFirstName());
        userDetailsDao.setPhone(userDetails.getPhone());
        return userDetailsDao;

    }

    public String getPhoneNumber(String email) {
        return userDetailsImpl.getPhoneNumber(email);
    }

    public Boolean checkIfUserExists(String email) {
        return userDetailsImpl.checkIfUserExist(email);
    }

    public UserDetails getUserDetails(String email) {
        return mapToUserDetails(userDetailsImpl.getUserdetails(email));

    }

    private UserDetails mapToUserDetails(UserDetailsDao userDetailsDao) {
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(userDetailsDao.getEmail());
        userDetails.setFirstName(userDetailsDao.getFirstName());
        userDetails.setLastName(userDetailsDao.getLastName());
        userDetails.setId((int) userDetailsDao.getId());
        System.out.println("Ser" + userDetails.getId());
        userDetails.setPassword(userDetailsDao.getPassword());
        userDetails.setPhone(userDetailsDao.getPhone());
        return userDetails;
    }

    public void updateUserDetails(UserDetails userDetails) {
        UserDetailsDao userDetailsDao = mapToUserDetailsDao(userDetails);
        userDetailsDao.setId(userDetails.getId());
        userDetailsImpl.updateUserDetails(userDetailsDao);
    }


}
