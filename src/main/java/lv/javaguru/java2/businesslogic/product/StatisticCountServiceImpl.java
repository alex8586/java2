package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.database.CountUsersDAO;
import lv.javaguru.java2.database.CountVisitorsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticCountServiceImpl implements StatisticCountService {

    @Autowired
    private CountVisitorsDAO countVisitorsDAO;
    @Autowired
    private CountUsersDAO countUsersDAO;

    @Override
    public long getProductViews(long productId) {
        long viewsVisitors;
        long viewsUsers;

        viewsVisitors = countVisitorsDAO.getCountByProductId(productId);
        viewsUsers = countUsersDAO.getCountByProductId(productId);
        return viewsUsers + viewsVisitors;
    }
}
