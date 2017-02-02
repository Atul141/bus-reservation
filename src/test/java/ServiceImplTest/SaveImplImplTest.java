package ServiceImplTest;


import Dao.UserDetailsDao;
import ServiceImpl.ConfigDB;
import ServiceImpl.SaveImpl;
import ServiceImpl.SyntaxSugar;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SaveImplImplTest {
    private Session session;
    private ConfigTest configTest;
    private ConfigDB configDB;
    private UserDetailsDao userDetailsDao;

    @Before
    public void before() {
        ConfigDB configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        configTest = new ConfigTest();
        session = configDB.getSession();
    }


    @Test
    public void shouldSaveToDatabase() {
        userDetailsDao = configTest.getUserDetailsinstance();
        SaveImpl saveImpl = new SaveImpl(configDB);
        saveImpl.saveToDb(userDetailsDao);
        configDB=new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        session=configDB.getSession();

    }

    @After
    public void delete() {
        configTest.delete(userDetailsDao);
    }
}
