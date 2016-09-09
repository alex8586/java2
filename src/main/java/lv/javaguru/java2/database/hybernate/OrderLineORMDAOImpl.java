package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.OrderLineDAO;
import lv.javaguru.java2.domain.order.OrderLine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ORM_OrderLineDAO")
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderLineORMDAOImpl implements OrderLineDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public long create(OrderLine orderLine){
        Session session = sessionFactory.getCurrentSession();
        session.persist(orderLine);
        session.flush();
        return orderLine.getId();
    }

    @Override
    public void update(OrderLine orderLine) {
        Session session = sessionFactory.getCurrentSession();
        session.update(orderLine);
    }

    @Override
    public void delete(OrderLine orderLine) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(orderLine);
    }

    @Override
    public OrderLine getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (OrderLine) session.get(OrderLine.class, id);
    }

    @Override
    public List<OrderLine> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(OrderLine.class).list();
    }

    @Override
    public List<OrderLine> getAllByOrderId(long id){
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(OrderLine.class)
                .add(Restrictions.eq("orderId", id)).list();
    }


}
