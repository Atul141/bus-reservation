package ServiceImpl;


import org.hibernate.Session;
import org.hibernate.Transaction;

public class SaveImpl {


    private ConfigDB configDB;
    public SaveImpl(ConfigDB configDB){
        this.configDB =configDB;
    }

    public void saveToDb(Object object) {
        try {
            Session session = configDB.getSession();
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
