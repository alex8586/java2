package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.CountVisitorsDAO;
import lv.javaguru.java2.domain.CountVisitor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ORM_CountVisitorsDAO")
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CountVisitorsORMDAOImpl implements CountVisitorsDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public long create(CountVisitor countVisitor) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(countVisitor);
        session.flush();
        return countVisitor.getId();
    }

    @Override
    public void update(CountVisitor countVisitor) {
        Session session = sessionFactory.getCurrentSession();
        session.update(countVisitor);
    }

    @Override
    public void delete(CountVisitor countVisitor) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(countVisitor);
    }

    @Override
    public CountVisitor getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CountVisitor) session.get(CountVisitor.class, id);
    }

    @Override
    public int getCountByProductId(long productId) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT sum(counter) FROM visitors_counter WHERE product_id=" + productId;
        int result = (int) session.createSQLQuery(sql).uniqueResult();
        return result;
    }

    @Override
    public int getCountByIp(String ip) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT sum(counter) FROM visitors_counter WHERE ip='" + ip + "'";
        int result = (int) session.createSQLQuery(sql).uniqueResult();
        return result;
    }

    @Override
    public int getSumCountFromAllTable() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT sum(counter) FROM visitors_counter";
        return (int) session.createSQLQuery(sql).uniqueResult();

    }

    @Override
    public List getAllCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(CountVisitor.class).list();
    }

}
