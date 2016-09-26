package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.StatisticLineDAO;
import lv.javaguru.java2.domain.StatisticLine;
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
        List<StatisticLine> stList = session.createCriteria(StatisticLine.class).list();
        return stList;

    }
}
