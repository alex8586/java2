package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.CountUsersDAO;
import lv.javaguru.java2.domain.CountUser;
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

@Component("ORM_CountUsersDAO")
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CountUsersORMDAOImpl implements CountUsersDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public long create(CountUser countUser) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(countUser);
        session.flush();
        return countUser.getId();
    }

    @Override
    public void update(CountUser countUser) {
        Session session = sessionFactory.getCurrentSession();
        session.update(countUser);
    }

    @Override
    public void delete(CountUser countUser) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(countUser);
    }

    @Override
    public CountUser getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CountUser) session.get(CountUser.class, id);
    }

    @Override
    public List<CountUser> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return (List<CountUser>) session.createCriteria(CountUser.class).list();
    }

    @Override
    public long getCountByProductId(long productId) {
        Session session = sessionFactory.getCurrentSession();
        Object result = session.createCriteria(CountUser.class)
                .add(Restrictions.eq("productId", productId))
                .setProjection(Projections.sum("counter")).uniqueResult();
        if (result == null)
            return 0;
        else
            return (long) result;
    }

    @Override
    public long getCountByUserId(long userId) {
        Session session = sessionFactory.getCurrentSession();
        return (long) session.createCriteria(CountUser.class)
                .add(Restrictions.eq("userId", userId))
                .setProjection(Projections.sum("counter")).uniqueResult();
    }

    public long getSumCountFromAllTable() {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.createCriteria(CountUser.class)
                .setProjection(Projections.sum("counter")).uniqueResult();
    }

    @Override
    public List getAllCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(CountUser.class).list();
    }

    @Override
    public CountUser getCountUserByUserIdProductId(long userId, long productId){
        Session session = sessionFactory.getCurrentSession();
        return (CountUser) session.createCriteria(CountUser.class)
                .add(Restrictions.eq("userId", userId))
                .add(Restrictions.eq("productId", productId))
                .setMaxResults(1).uniqueResult();
    }

}
