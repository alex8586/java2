package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ORM_UserDAO")
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserORMDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public long create(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        session.flush();
        return user.getId();
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public User getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.get(User.class, id);
    }

    @Override
    public List getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(User.class).list();
    }

    @Override
    public User getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
    }
}