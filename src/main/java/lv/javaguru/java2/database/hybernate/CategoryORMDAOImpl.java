package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.domain.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component("ORM_CategoryDAO")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CategoryORMDAOImpl implements CategoryDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public long create(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(category);
        session.flush();
        return category.getId();
    }

    @Override
    public void update(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.update(category);
    }

    @Override
    public void delete(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(category);
    }

    @Override
    public Category getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Category) session.get(Category.class, id);
    }

    @Override
    public List getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Category.class).list();
    }

}