package ServiceImpl;


import Config.HibernateUtil;
import Dao.LoginDao;
import Dao.UserDetailsDao;
import Models.UserDetails;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class LoginImpl {

        public boolean validateLogin(LoginDao loginDao){
            Session session = new Configuration().configure().buildSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List employees = session.createQuery("FROM UserDetailsDao  user where user.id=6004").list();
            transaction.commit();
            session.close();
            if(!employees.isEmpty())
            return  true;
            return false;
        }
}
