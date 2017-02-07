package ServiceImpl;


import Dao.LoginDao;
import Dao.OrderDetailsDao;
import Dao.UserDetailsDao;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class LoginImpl {

    private ConfigDB configDB;

    public LoginImpl(ConfigDB configDB) {
        this.configDB = configDB;
    }

    public boolean validateLogin(LoginDao loginDao) {
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        String query = "FROM UserDetailsDao  user where user.email=" + "'" + loginDao.getEmail() + "'";
        UserDetailsDao userDetails;
        try {
            userDetails = (UserDetailsDao) session.createQuery(query).uniqueResult();
            session.lock(userDetails, LockMode.READ);
            transaction.commit();
            session.close();
            if (userDetails != null) {
                if (userDetails.getPassword().equals(loginDao.getPassword()))
                    return true;

            }
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
        }

        return false;
    }
}
