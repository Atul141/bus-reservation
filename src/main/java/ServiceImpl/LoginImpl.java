package ServiceImpl;


import Dao.LoginDao;
import Dao.UserDetailsDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class LoginImpl {

    private ConfigDB configDB;

    public LoginImpl() {
        configDB = new ConfigDB();
    }

    public boolean validateLogin(LoginDao loginDao) {
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        String query = "FROM UserDetailsDao  user where user.email=" + "'" + loginDao.getEmail() + "'";
        UserDetailsDao userDetails;
        try {
            userDetails = (UserDetailsDao) session.createQuery(query).uniqueResult();
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
