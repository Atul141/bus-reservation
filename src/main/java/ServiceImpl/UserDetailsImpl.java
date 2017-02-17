package ServiceImpl;

import Dao.UserDetailsDao;
import Models.UserDetails;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsImpl {

    private ConfigDB configDB;
    private SaveImpl saveImpl;

    public UserDetailsImpl(ConfigDB configDB) {
        this.configDB = configDB;
        saveImpl = new SaveImpl(configDB);
    }

    public void saveToDb(UserDetailsDao userDetails) {
        saveImpl.saveToDb(userDetails);


    }

    public Boolean checkIfUserExist(String email) {
        UserDetailsDao userDetailsDao = getUserdetails(email);
        if (userDetailsDao == null)
            return false;
        return true;
    }

    public String getPhoneNumber(String email) {
        UserDetailsDao userDetailsDao = getUserdetails(email);
        return userDetailsDao.getPhone();
    }

    public UserDetailsDao getUserdetails(String email) {
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        String query = "FROM UserDetailsDao  user where user.email=" + "'" + email + "'";
        UserDetailsDao userDetails = null;
        try {
            userDetails = (UserDetailsDao) session.createQuery(query).uniqueResult();
            session.contains(email, userDetails);
            session.lock(userDetails, LockMode.READ);
            transaction.commit();
            session.close();

        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
            return null;
        }
        return userDetails;
    }

    public void updateUserDetails(UserDetailsDao userDetailsDao) {
        UpdateImpl update = new UpdateImpl(configDB);
        update.UpdateDb(userDetailsDao);
    }

    public List<String> getAllPhoneNumberList() {
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        String query = "FROM UserDetailsDao  user";
        List<UserDetailsDao> userDetailsDaoList;
        try {
            userDetailsDaoList = (List<UserDetailsDao>) session.createQuery(query).list();
            session.lock(userDetailsDaoList, LockMode.READ);
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
            return null;
        }
        return getPhoneNumberList(userDetailsDaoList);
    }

    private List<String> getPhoneNumberList(List<UserDetailsDao> userDetailsDaoList) {
        List<String> phoneNumberList = new ArrayList<String>();
        System.out.println(phoneNumberList.size());
        for (UserDetailsDao userDetailsDao : userDetailsDaoList) {
            phoneNumberList.add(userDetailsDao.getPhone());
        }
        return phoneNumberList;
    }
}

