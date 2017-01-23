package ServiceImpl;


import Dao.LoginDao;
import Dao.UserDetailsDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class LoginImpl {

    public UserDetailsDao validateLogin(LoginDao loginDao) {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String qeury = "FROM UserDetailsDao  user where user.email=" + "'" + loginDao.getEmail() + "'";
        UserDetailsDao userDetails;
        userDetails = (UserDetailsDao) session.createQuery(qeury).uniqueResult();
        transaction.commit();
        session.close();
        if (userDetails != null) {
            if (userDetails.getPassword().equals(loginDao.getPassword()))
                return userDetails;

        }
        return null;
    }
}
