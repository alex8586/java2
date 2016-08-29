package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ORM_ProductDAO")
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductORMDAOImpl implements ProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public long create(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(product);
        session.flush();
        return product.getId();
    }

    @Override
    public void update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
    }

    @Override
    public void delete(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(product);
    }

    @Override
    public Product getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Product) session.get(Product.class, id);
    }

    @Override
    public List getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Product.class).list();
    }

    @Override
    public List getAllByCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Product.class).add(Restrictions.eq("categoryId", category.getId())).list();
    }
}