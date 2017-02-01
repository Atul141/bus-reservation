package ServiceImpl;


import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class ConfigDB {

    public Session getSession() {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        return session;
    }
}
