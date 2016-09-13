package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.StockDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Stock;
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

import java.util.Date;
import java.util.List;

@Component("ORM_StockDAO")
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StockORMDAOImpl implements StockDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public long create(Stock stock) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(stock);
        session.flush();
        return stock.getId();
    }

    @Override
    public void update(Stock stock) {
        Session session = sessionFactory.getCurrentSession();
        session.update(stock);
    }

    @Override
    public void delete(Stock stock) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(stock);
    }

    @Override
    public Stock getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Stock) session.get(Stock.class, id);
    }

    @Override
    public List<Stock> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Stock.class).list();
    }

    @Override
    public List<Stock> allByProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        return (List<Stock>) session.createCriteria(Stock.class).add(Restrictions.eq("productId", product.getId())).list();
    }

    @Override
    public long countFreshByProduct(Product product, Date date) {
        SimpleExpression expression = Restrictions.ge("expireDate", date);
        return countProductWithCriteria(product, expression);
    }
    @Override
    public long countExpiredByProduct(Product product, Date date) {
        SimpleExpression expression = Restrictions.le("expireDate", date);
        return countProductWithCriteria(product, expression);
    }

    @Override
    public List<Stock> expiredByDateRange(Date start, Date end){
        Session session = sessionFactory.getCurrentSession();
        return (List<Stock>)session.createCriteria(Stock.class)
                .add(Restrictions.ge("expireDate", start))
                .add(Restrictions.le("expireDate", end)).list();
    }

    private long countProductWithCriteria(Product product, SimpleExpression expression) {
        if (expression.getValue() == null)
            throw new NullPointerException("date = null");
        Session session = sessionFactory.getCurrentSession();
        Object result = session.createCriteria(Stock.class)
                .add(Restrictions.eq("productId", product.getId()))
                .add(expression)
                .setProjection(Projections.sum("quantity")).uniqueResult();

        if (result == null)
            return 0;

        return (long) result;
    }

}
