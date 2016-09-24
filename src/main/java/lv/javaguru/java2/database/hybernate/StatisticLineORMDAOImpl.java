package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.crossdomain.StatisticLine;
import lv.javaguru.java2.database.StatisticLineDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
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
        return session.createSQLQuery("SELECT  products.id AS productId , products.name AS productName,\n" +
                "          categories.id AS categoryId, categories.name AS categoryName,\n" +
                "          count(reviews.id) AS reviewCount,\n" +
                "          COALESCE(sum(users_counter.counter),0) AS userVisits ,\n" +
                "          COALESCE(sum(visitors_counter.counter),0) AS visitorVisits,\n" +
                "          COALESCE(avg(rate.rate),0) AS avgRate\n" +
                "  FROM products\n" +
                "    LEFT OUTER JOIN categories\n" +
                "      ON products.category_id = categories.id\n" +
                "    LEFT OUTER JOIN reviews\n" +
                "      ON reviews.product_id = products.id\n" +
                "    LEFT OUTER JOIN visitors_counter\n" +
                "      ON visitors_counter.product_id = products.id\n" +
                "    LEFT OUTER JOIN users_counter\n" +
                "      ON users_counter.product_id = products.id\n" +
                "    LEFT OUTER JOIN rate\n" +
                "      ON rate.product_id = products.id\n" +
                "  GROUP BY products.id;")
                .setResultTransformer(new AliasToBeanResultTransformer(StatisticLine.class))
                .list();
    }
}
