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

        try {
            viewsVisitors = countVisitorsDAO.getCountByProductId(productId);
        } catch (NullPointerException e) {
            viewsVisitors = 0;
        }
        try {
            viewsUsers = countUsersDAO.getCountByProductId(productId);
        } catch (NullPointerException e) {
            viewsUsers = 0;
        }

        return viewsUsers + viewsVisitors;
    }
}
