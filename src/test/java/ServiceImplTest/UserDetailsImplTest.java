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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDetailsImplTest {


    private Session session;
    private ConfigDB configDB;
    private UserDetailsDao userDetailsDao;
    private ConfigTest configTest;
    private UserDetailsImpl userDetailsImpl;

    @Before
    public void before() {
        configDB = new ConfigDB();
        configTest = new ConfigTest();
        userDetailsImpl = new UserDetailsImpl(configDB);
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        userDetailsDao = configTest.getUserDetailsinstance();

    }

    @Test
    public void shouldSaveTheUserDetails() {
        userDetailsImpl.saveToDb(userDetailsDao);
        session = configDB.getSession();

        String query = "FROM UserDetailsDao  user where user.email=" + "'" + userDetailsDao.getEmail() + "'";
        Transaction transaction = session.beginTransaction();
        UserDetailsDao userDetails = (UserDetailsDao) session.createQuery(query).uniqueResult();
        transaction.commit();
        assertEquals("1123456", userDetails.getPhone());

    }

    @Test
    public void shouldCheckIfUserExists() {
        userDetailsImpl.saveToDb(userDetailsDao);
        assertTrue(userDetailsImpl.checkIfUserExist(userDetailsDao.getEmail()));

    }

    @Test
    public void shouldReturnPhoneNumber() {
        userDetailsImpl.saveToDb(userDetailsDao);
        assertEquals(userDetailsDao.getPhone(), userDetailsImpl.getPhoneNumber(userDetailsDao.getEmail()));
    }

    @Test
    public void shouldGetUserDetails() {
        userDetailsImpl.saveToDb(userDetailsDao);
        UserDetailsDao userDetailsExpected = userDetailsImpl.getUserdetails(userDetailsDao.getEmail());
        assertEquals(userDetailsExpected.getId(), userDetailsDao.getId());
    }

    @Test
    public void shouldUpdateUserDetails() {
        userDetailsImpl.saveToDb(userDetailsDao);
        userDetailsDao.setPassword("11111");
        userDetailsImpl.updateUserDetails(userDetailsDao);

        session = configDB.getSession();
        String query = "FROM UserDetailsDao  user where user.email=" + "'" + userDetailsDao.getEmail() + "'";
        Transaction transaction = session.beginTransaction();
        UserDetailsDao userDetails = (UserDetailsDao) session.createQuery(query).uniqueResult();
        transaction.commit();
        assertEquals("11111", userDetails.getPassword());

    }

    @Test
    public void shouldGetPhoneNumberList() {
        userDetailsImpl.saveToDb(userDetailsDao);
        List<String> phoneNumberList = userDetailsImpl.getAllPhoneNumberList();
        assertEquals(phoneNumberList.get(0), userDetailsDao.getPhone());
    }

    @After
    public void delete() {
        configTest.delete(userDetailsDao);
    }


}
