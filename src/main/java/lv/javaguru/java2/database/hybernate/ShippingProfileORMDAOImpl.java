package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.ShippingProfileDAO;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ORM_ShippingProfileDAO")
@Transactional
public class ShippingProfileORMDAOImpl implements ShippingProfileDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public long create(ShippingProfile shippingProfile) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(shippingProfile);
        session.flush();
        return shippingProfile.getId();
    }

    @Override
    public void update(ShippingProfile shippingProfile) {
        Session session = sessionFactory.getCurrentSession();
        session.update(shippingProfile);
    }

    @Override
    public void delete(ShippingProfile shippingProfile) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(shippingProfile);
    }

    @Override
    public ShippingProfile getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (ShippingProfile) session.get(ShippingProfile.class, id);
    }

    @Override
    public List getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(ShippingProfile.class).list();
    }

    @Override
    public List getAllByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(ShippingProfile.class).add(Restrictions.eq("user_id", user.getId())).list();
    }
}