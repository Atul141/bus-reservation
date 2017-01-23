package ServiceImpl;


import Dao.LoginDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class LoginImpl {

        public boolean validateLogin(LoginDao loginDao){
            Session session = new Configuration().configure().buildSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String qeury="FROM UserDetailsDao  user where user.email="+"'"+loginDao.getEmail()+"'";
            List employees;
            employees = session.createQuery(qeury).list();
            transaction.commit();
            session.close();
            if(!employees.isEmpty())
            return  true;
            return false;
        }
}
