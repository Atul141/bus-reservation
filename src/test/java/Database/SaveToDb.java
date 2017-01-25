package Database;


import Dao.UserDetailsDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SaveToDb {
    private SessionFactory sessionFactory;
    private Session session = null;
    @Before
    public void before() {
      Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDetailsDao.class);
        configuration.setProperty("hibernate.dialect",
                "org.hibernate.dialect.PostgreSQL94Dialect");
        configuration.setProperty("hibernate.connection.driver_class",
                "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/testdb");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public UserDetailsDao getUserDetailsinstance(){
        UserDetailsDao userDetailsDao=new UserDetailsDao();
        userDetailsDao.setFirstName("abc");
        userDetailsDao.setLastName("def");
        userDetailsDao.setEmail("abcd@gmail.com");
        userDetailsDao.setPassword("password");
        userDetailsDao.setPhone("1123456789");
        return userDetailsDao;
    }
    @Test
    public void shouldSaveToDatabase(){
      UserDetailsDao userDetailsDao=getUserDetailsinstance();
       UserDetailsDao userDetails;
        Transaction transaction = session.beginTransaction();
        session.persist(userDetailsDao);
        session.flush();
        transaction.commit();
        String query="FROM UserDetailsDao  user where user.email="+"'"+userDetailsDao.getEmail()+"'";
        userDetails = (UserDetailsDao) session.createQuery(query).uniqueResult();
        assertEquals(userDetails.getEmail(),userDetailsDao.getEmail());
        Transaction delete = session.beginTransaction();
        session.delete(userDetailsDao);
        delete.commit();
        session.close();
    }
}
