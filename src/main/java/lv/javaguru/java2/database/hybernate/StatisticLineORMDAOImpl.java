package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.crossdomain.StatisticLine;
import lv.javaguru.java2.database.StatisticLineDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ORM_StatisticLineDAO")
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StatisticLineORMDAOImpl implements StatisticLineDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<StatisticLine> getAll(){
        Session session = sessionFactory.getCurrentSession();
        return session.createSQLQuery("SELECT * FROM product_statistics").list();
    }
}
