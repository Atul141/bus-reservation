package ServiceImpl;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UpdateImpl {

    private ConfigDB configDB;

    public UpdateImpl() {
        configDB = new ConfigDB();
    }

    public void UpdateDb(Object object) {
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(object);
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
        }
    }


}
