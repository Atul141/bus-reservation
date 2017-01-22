package Services;

import Models.UserDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDetailsService {


    public void saveUserDetails(UserDetails userDetails){

        System.out.println("Firstname "+ userDetails.getFirstName());
        System.out.println("LastName "+ userDetails.getLastName());
        System.out.println("Password "+ userDetails.getPassword());

        Session session = new Configuration().configure().buildSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        session.persist(userDetails);
        transaction.commit();
        session.flush();
        session.close();
    }
}
