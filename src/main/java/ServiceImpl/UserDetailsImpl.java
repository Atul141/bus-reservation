package ServiceImpl;

import Dao.UserDetailsDao;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
            return true;
        }
        if (userDetails == null)
            return false;
        return true;
    }
}
