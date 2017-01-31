package Database;


import Dao.UserDetailsDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SaveToDb {
    private Session session = null;
    private ConfigTest configTest;

    @Before
    public void before() {
        configTest = new ConfigTest();
        session = configTest.getTestSession();
    }


    @Test
    public void shouldSaveToDatabase() {
        UserDetailsDao userDetailsDao = configTest.getUserDetailsinstance();
        UserDetailsDao userDetails;
        Transaction transaction = session.beginTransaction();
        session.persist(userDetailsDao);
        session.flush();
        transaction.commit();
        String query = "FROM UserDetailsDao  user where user.email=" + "'" + userDetailsDao.getEmail() + "'";
        userDetails = (UserDetailsDao) session.createQuery(query).uniqueResult();
        assertEquals(userDetails.getEmail(), userDetailsDao.getEmail());
        Transaction delete = session.beginTransaction();
        session.delete(userDetailsDao);
        delete.commit();
        session.close();
    }
}
