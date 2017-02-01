package ServiceImpl;


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
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
        }
    }


}
