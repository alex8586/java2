package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.ReviewDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Review;
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

@Component("ORM_ReviewDAO")
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ReviewDAOImpl implements ReviewDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public long create(Review review) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(review);
        session.flush();
        return review.getId();
    }

    @Override
    public void update(Review review) {
        Session session = sessionFactory.getCurrentSession();
        session.update(review);
    }

    @Override
    public void delete(Review review) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(review);
    }

    @Override
    public Review getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Review) session.get(Product.class, id);
    }

    @Override
    public List<Review> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Review.class).list();
    }

    @Override
    public List<Review> getByProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Review.class).add(Restrictions.eq("productId", product.getId())).list();
    }

    @Override
    public List<Review> getByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Review.class).add(Restrictions.eq("userId", user.getId())).list();
    }

    @Override
    public List<Review> getByUserAndProduct(Product product, User user) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Review.class)
                .add(Restrictions.eq("userId", user.getId()))
                .add(Restrictions.eq("productId", product.getId()))
                .list();
    }

}
