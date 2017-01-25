package ServiceImpl;

import Dao.UserDetailsDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDetailsImpl {


    public void saveUserDetails(UserDetailsDao userDetails) {


        try {

            Session session = new Configuration().configure().buildSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.persist(userDetails);
            session.flush();
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session "+ex);

        }
    }

}
