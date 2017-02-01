package ServiceImpl;


import org.hibernate.Session;

import org.hibernate.cfg.Configuration;

import static ServiceImpl.SyntaxSugar.PROD_ENV;

public class ConfigDB {

    private String environment;

    public ConfigDB() {
        environment = PROD_ENV;
    }

    public Session getSession() {

        if (environment == PROD_ENV) {

            Session session = new Configuration().configure().buildSessionFactory().openSession();
            return session;
        }
        Session session = new Configuration().configure("hibernateTest.cfg.xml").buildSessionFactory().openSession();
        return session;

    }

    public void setEnvironment(String env) {
        environment = env;
    }

}
