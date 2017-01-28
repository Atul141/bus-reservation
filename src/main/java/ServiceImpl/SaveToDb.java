package ServiceImpl;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SaveToDb {


    public void saveToDb(Object object) {
        try {
            Session session = new Configuration().configure().buildSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(object);
            session.flush();
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);

        }
    }
}
