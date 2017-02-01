package ServiceImpl;


import Dao.*;
import org.hibernate.Session;
import java.io.*;
import java.nio.file.*;

import org.hibernate.SessionFactory;
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

    private Session getTestSession() {
        SessionFactory sessionFactory;
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDetailsDao.class);
        configuration.addAnnotatedClass(RouteDao.class);
        configuration.addAnnotatedClass(SeatsDao.class);
        configuration.addAnnotatedClass(BusDao.class);
        configuration.addAnnotatedClass(PassengerDao.class);
        configuration.addAnnotatedClass(OrderDetailsDao.class);
        configuration.setProperty("hibernate.dialect",
                "org.hibernate.dialect.PostgreSQL94Dialect");
        configuration.setProperty("hibernate.connection.driver_class",
                "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/testdb");
//        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }
}
