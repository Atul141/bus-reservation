package ServiceImplTest;


import Dao.UserDetailsDao;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import ServiceImpl.UserDetailsImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserRegistrationControllerImplTest {


    private Session session;
    private ConfigDB configDB;
    private UserDetailsDao userDetailsDao;
    private ConfigTest configTest;

    @Before
    public void before() {
        configDB = new ConfigDB();
       configTest = new ConfigTest();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        userDetailsDao = configTest.getUserDetailsinstance();

    }

    @Test
    public void shouldSaveTheUserDetails() {
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(configDB);
        userDetailsImpl.saveToDb(userDetailsDao);

        configDB=new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        session=configDB.getSession();

        String query = "FROM UserDetailsDao  user where user.email=" + "'" + userDetailsDao.getEmail() + "'";
        Transaction transaction = session.beginTransaction();
        UserDetailsDao userDetails = (UserDetailsDao) session.createQuery(query).uniqueResult();
        transaction.commit();
        assertEquals("1123456", userDetails.getPhone());

    }
    @After
    public void delete(){
        configTest.delete(userDetailsDao);
    }


}
