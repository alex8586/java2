package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.helpers.CategoryTree;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("ORM_ProductDAO")
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductORMDAOImpl implements ProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    CategoryTree categoryTree;

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

    @Override
    public List getCategoryContent(Category category) {
        System.out.println(categoryTree);
        List<Category> categories = categoryTree.getAncestors(category);
        List<Long> ids = categories.stream().map(cat -> cat.getId()).collect(Collectors.toList());
        ids.add(category.getId());
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Product.class).add(Restrictions.in("categoryId", ids)).list();
    }

    @Override
    public Product getRandomProduct() {
        Session session = sessionFactory.getCurrentSession();
        return (Product) session.createCriteria(Product.class)
                .add(Restrictions.sqlRestriction("1=1 ORDER BY RAND()"))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public Product getRandomProductWithoutCurrentCategoryId(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Product) session.createCriteria(Product.class)
                .add(Restrictions.sqlRestriction("1=1 order by rand() where category_id not like" + id))
                .setMaxResults(1);
    }
}