package ServiceImpl;


import Dao.OrderDetailsDao;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UpdateImpl {

    private ConfigDB configDB;

    public UpdateImpl(ConfigDB configDB) {
        this.configDB = configDB;
    }

    public void UpdateDb(Object object) {
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(object);
            session.lock(object, LockMode.READ);
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
        }
    }


}
