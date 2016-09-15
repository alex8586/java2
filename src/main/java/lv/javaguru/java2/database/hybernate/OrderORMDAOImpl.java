package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.domain.order.Order;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ORM_OrderDAO")
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderORMDAOImpl implements OrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public long create(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(order);
        session.flush();
        return order.getId();
    }

    @Override
    public void update(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.update(order);
    }

    @Override
    public void delete(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(order);
    }

    @Override
    public Order getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Order) session.get(Order.class, id);
    }

    @Override
    public List<Order> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Order.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public List<Order> getByUserId(long id){
        Session session = sessionFactory.getCurrentSession();
        return (List<Order>) session.createCriteria(Order.class)
                .add(Restrictions.eq("userId", id))
                .addOrder(org.hibernate.criterion.Order.asc("orderDate"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<Order> getAllOrderLinesByOrderId(long id){
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Order.class)
                .add(Restrictions.eq("id",id)).list();
    }
}
