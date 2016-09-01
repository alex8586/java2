package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.CountVisitorsDAO;
import lv.javaguru.java2.domain.CountCustomer;
import lv.javaguru.java2.domain.CountVisitor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ORM_CountVisitorsDAO")
@Transactional
public class CountVisitorsORMDAOImpl implements CountVisitorsDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public int getCountByProduct(long productId) {
        Session session = sessionFactory.getCurrentSession();
        return (int) session.get(CountCustomer.class, productId);
    }

    @Override
    public int getCountByIp(String ip) {
        Session session = sessionFactory.getCurrentSession();
        return (int) session.get(CountCustomer.class, ip);
    }

    @Override
    public int getCountByProductAndVisitor(long productId, long ip) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT * FROM visitors_counter WHERE product_id="+productId+" and ip="+ip;
        int result = (int) session.createSQLQuery(sql).uniqueResult();
        return result;
    }

    @Override
    public List getAllCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(CountVisitor.class).list();
    }

    @Override
    public void createCountVisitor(CountVisitor countVisitor){
        Session session = sessionFactory.getCurrentSession();
        session.persist(countVisitor);
    }

    @Override
    public void updateCountVisitor(CountVisitor countVisitor){
        Session session = sessionFactory.getCurrentSession();
        session.update(countVisitor);
    }


}
