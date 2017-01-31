package ServiceImplTest;


import Dao.UserDetailsDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserRegistrationImplTest {


    private Session session = null;
    private UserDetailsDao userDetailsDao;

    @Before
    public void before() {
        ConfigTest configTest = new ConfigTest();
        session = configTest.getTestSession();
        userDetailsDao = configTest.getUserDetailsinstance();
        Transaction transaction = session.beginTransaction();
        session.persist(userDetailsDao);
        session.flush();
        transaction.commit();
    }

    @Test
    public void shouldSaveTheUserDetails() {
        String query = "FROM UserDetailsDao  user where user.email=" + "'" + userDetailsDao.getEmail() + "'";
        Transaction transaction = session.beginTransaction();
        UserDetailsDao userDetails = (UserDetailsDao) session.createQuery(query).uniqueResult();
        transaction.commit();
        Transaction delete = session.beginTransaction();
        session.delete(userDetailsDao);
        delete.commit();
        session.close();
        assertEquals("1123456789", userDetails.getPhone());

    }



}
