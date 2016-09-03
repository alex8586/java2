package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.CountUsersDAO;
import lv.javaguru.java2.domain.CountUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
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
    public int getCountByProductId(long productId) {
        Session session = sessionFactory.getCurrentSession();

        return (int) session.createCriteria(CountUser.class)
                .add(Restrictions.eq("product_id", productId))
                .setProjection(Projections.sum("counter")).uniqueResult();
    }

    @Override
    public int getCountByUserId(long userId) {
        Session session = sessionFactory.getCurrentSession();
//        String sql = "SELECT sum(counter) FROM users_counter WHERE user_id=" + userId;
//        int result = (int) session.createSQLQuery(sql).uniqueResult();
//        return result;
        return (int) session.createCriteria(CountUser.class)
                .add(Restrictions.eq("user_id",userId))
                .setProjection(Projections.sum("counter")).uniqueResult();
    }

    public int getSumCountFromAllTable() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT sum(counter) FROM users_counter";
        int result = (int) session.createSQLQuery(sql).uniqueResult();
        return result;
    }

    @Override
    public List getAllCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(CountUser.class).list();
    }

    private int countCountWithCriteria(CountUser countUser, SimpleExpression expression) {
        if (expression.getValue() == null)
            throw new NullPointerException("countUser == null");
        Session session = sessionFactory.getCurrentSession();
        Object result = session.createCriteria(CountUser.class)
                .add(Restrictions.eq("counter", countUser.getCounter()))
                .add(expression)
                .setProjection(Projections.sum("quantity")).uniqueResult();

        if (result == null)
            return 0;

        return (int) result;
    }
}
