package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.CountCustomersDAO;
import lv.javaguru.java2.domain.CountCustomer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ORM_CountCustomersDAO")
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CountCustomersORMDAOImpl implements CountCustomersDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public long create(CountCustomer countCustomer){
        Session session = sessionFactory.getCurrentSession();
        session.persist(countCustomer);
        session.flush();
        return countCustomer.getId();
    }

    @Override
    public void update(CountCustomer countCustomer){
        Session session = sessionFactory.getCurrentSession();
        session.update(countCustomer);
    }

    @Override
    public CountCustomer getById(long id){
        Session session = sessionFactory.getCurrentSession();
        return (CountCustomer) session.get(CountCustomer.class, id);
    }

    @Override
    public int getCountByProduct(long productId) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT counter FROM customers_counter WHERE product_id="+productId;
        int result = (int) session.createSQLQuery(sql).uniqueResult();
        return result;
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

}
