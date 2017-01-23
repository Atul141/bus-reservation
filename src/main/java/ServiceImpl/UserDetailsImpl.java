package ServiceImpl;

import Dao.UserDetailsDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDetailsImpl {


    public void saveUserDetails(UserDetailsDao userDetails) {

        System.out.println("Firstname " + userDetails.getFirstName());
        System.out.println("LastName " + userDetails.getLastName());
        System.out.println("Password " + userDetails.getPassword());

        try {


            Session session = new Configuration().configure().buildSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(userDetails);
        session.flush();
            transaction.commit();
            session.close();
        } catch (javax.persistence.PersistenceException e) {

        }

    }

}
