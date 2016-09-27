package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.crossdomain.StockProduct;
import lv.javaguru.java2.database.StockProductDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ORM_StockProductDAO")
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StockProductORMDAOImpl implements StockProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<StockProduct> getAll(){
        Session session = sessionFactory.getCurrentSession();
        List<StockProduct> list = session.createCriteria(StockProduct.class).list();
        return list;
    }
}
