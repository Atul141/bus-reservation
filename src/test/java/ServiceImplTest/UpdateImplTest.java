package ServiceImplTest;


import Dao.OrderDetailsDao;
import Dao.UserDetailsDao;
import Models.UserDetails;
import ServiceImpl.*;
import ServiceImpl.SaveToDb;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UpdateImplTest {

    private ConfigTest configTest;
    private ConfigDB configDB;
    private UpdateImpl updateImpl;
    private UserDetailsDao userDetailsDao;

    @Before
    public void setup() {
        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        configTest = new ConfigTest();
        updateImpl = new UpdateImpl(configDB);
        userDetailsDao = configTest.getUserDetailsinstance();
    }

    @Test
    public void shouldAbleToUpdate() {
        ServiceImpl.SaveToDb saveToDb = new SaveToDb(configDB);
        saveToDb.saveToDb(userDetailsDao);

        userDetailsDao.setEmail("abcd@gmail.com");
        updateImpl.UpdateDb(userDetailsDao);
        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        Session session = configDB.getSession();
        String query = "FROM UserDetailsDao  user where user.id= 1";
        Transaction transaction = session.beginTransaction();
        UserDetailsDao userDetailsDao1 = (UserDetailsDao) session.createQuery(query).uniqueResult();
        transaction.commit();

        assertEquals("abcd@gmail.com", userDetailsDao1.getEmail());

    }
}
