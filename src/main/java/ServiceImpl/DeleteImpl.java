package ServiceImpl;


import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteImpl {
    private ConfigDB configDB;

    public DeleteImpl(ConfigDB configDB) {
        this.configDB = configDB;
    }

    public void deleteDb(Object object) {
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(object);
            session.lock(object, LockMode.READ);
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
        }
    }

}
