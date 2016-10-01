package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.CountVisitorsDAO;
import lv.javaguru.java2.domain.CountVisitor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
    public long getCountByProductId(long productId) {
        Session session = sessionFactory.getCurrentSession();
        Object result = session.createCriteria(CountVisitor.class)
                .add(Restrictions.eq("productId", productId))
                .setProjection(Projections.sum("counter")).uniqueResult();
        if (result == null)
            return 0;
        else
            return (long) result;
    }

    @Override
    public long getCountByIp(String ip) {
        Session session = sessionFactory.getCurrentSession();
        return (long) session.createCriteria(CountVisitor.class)
                .add(Restrictions.eq("ip", ip))
                .setProjection(Projections.sum("counter")).uniqueResult();
    }

    @Override
    public long getSumCountFromAllTable() {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.createCriteria(CountVisitor.class)
                .setProjection(Projections.sum("counter")).uniqueResult();

    }

    @Override
    public List getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(CountVisitor.class).list();
    }

    @Override
    public CountVisitor getCountUserByUserIpProductId(String ip, long productId){
        Session session = sessionFactory.getCurrentSession();
        return (CountVisitor) session.createCriteria(CountVisitor.class)
                .add(Restrictions.eq("ip", ip))
                .add(Restrictions.eq("productId", productId))
                .setMaxResults(1).uniqueResult();
    }

}
