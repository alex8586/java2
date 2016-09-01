package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.CountCustomersDAO;
import lv.javaguru.java2.domain.CountCustomer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ORM_CountCustomersDAO")
@Transactional
public class CountCustomersORMDAOImpl implements CountCustomersDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public int getCountByProduct(long productId) {
        Session session = sessionFactory.getCurrentSession();
        return (int) session.get(CountCustomer.class, productId);
    }

    @Override
    public int getCountByCustomer(long userId) {
        Session session = sessionFactory.getCurrentSession();
        return (int) session.get(CountCustomer.class, userId);
    }

    public int getCountByProductAndVisitor(long productId, long userId) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT * FROM customers_counter WHERE product_id="+productId+" and user_id="+userId;
        int result = (int) session.createSQLQuery(sql).uniqueResult();
        return result;
    }

    @Override
    public List getAllCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(CountCustomer.class).list();
    }

    @Override
    public void createCountCustomer(CountCustomer countCustomer){
        Session session = sessionFactory.getCurrentSession();
        session.persist(countCustomer);
    }

    @Override
    public void updateCountCustomer(CountCustomer countCustomer){
        Session session = sessionFactory.getCurrentSession();
        session.update(countCustomer);
    }
}
