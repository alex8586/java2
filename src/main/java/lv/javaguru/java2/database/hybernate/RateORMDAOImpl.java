package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.RateDAO;
import lv.javaguru.java2.domain.Rate;
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

@Transactional
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RateORMDAOImpl implements RateDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long create(Rate rate) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(rate);
        session.flush();
        return rate.getId();

    }

    @Override
    public void update(Rate rate) {
        Session session = sessionFactory.getCurrentSession();
        session.update(rate);
    }

    @Override
    public void delete(Rate rate) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(rate);
    }

    @Override
    public Rate getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Rate) session.get(Rate.class, id);
    }

    @Override
    public List<Rate> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Rate.class).list();
    }

    @Override
    public Rate getByUserIdAndProductId(long userId, long productId){
        Session session = sessionFactory.getCurrentSession();
        return (Rate) session.createCriteria(Rate.class)
                .add(Restrictions.eq("userId", userId))
                .add(Restrictions.eq("productId", productId))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public List<Rate> getByProductId(long productId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Rate.class)
                .add(Restrictions.eq("productId", productId))
                .list();
    }

    @Override
    public double getAverageRate(long productId){
        Session session = sessionFactory.getCurrentSession();
        return (double) session.createCriteria(Rate.class)
                .add(Restrictions.eq("productId", productId))
                .setProjection(Projections.avg("rate")).uniqueResult();
    }
}
