package lv.javaguru.java2.businesslogic.admin;

import lv.javaguru.java2.crossdomain.StatisticLine;
import lv.javaguru.java2.database.StatisticLineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticLineDAO statisticLineDAO;

    @Override
    public Map<String, Object> model(){
        Map<String, Object> model = new HashMap<>();
        List<StatisticLine> list = statisticLineDAO.getAll();
        model.put("statisticList", list);
        return model;
    }

    @Override
    public Map<String, Object> sortBy(String sortValue){
        Map<String, Object> model = new HashMap<>();
        if(sortValue.equals("productId")){
            List<StatisticLine> list = statisticLineDAO.getAll();
            Collections.sort(list,(StatisticLine l1, StatisticLine l2)
                    -> l1.getProductId().compareTo(l2.getProductId()));
            model.put("statisticList", list);
        }
        if(sortValue.equals("productName")){
            List<StatisticLine> list = statisticLineDAO.getAll();
            Collections.sort(list,(StatisticLine l1, StatisticLine l2)
                    -> l1.getProductName().compareTo(l2.getProductName()));
            model.put("statisticList", list);
        }
        if(sortValue.equals("categoryId")){
            List<StatisticLine> list = statisticLineDAO.getAll();
            Collections.sort(list,(StatisticLine l1, StatisticLine l2)
                    -> l1.getCategoryId().compareTo(l2.getCategoryId()));
            model.put("statisticList", list);
        }
        if(sortValue.equals("categoryName")){
            List<StatisticLine> list = statisticLineDAO.getAll();
            Collections.sort(list,(StatisticLine l1, StatisticLine l2)
                    -> l1.getCategoryName().compareTo(l2.getCategoryName()));
            model.put("statisticList", list);
        }
        if(sortValue.equals("reviewCount")){
            List<StatisticLine> list = statisticLineDAO.getAll();
            Collections.sort(list,(StatisticLine l1, StatisticLine l2)
                    -> l2.getReviewCount().compareTo(l1.getReviewCount()));
            model.put("statisticList", list);
        }
        if(sortValue.equals("userVisits")){
            List<StatisticLine> list = statisticLineDAO.getAll();
            Collections.sort(list,(StatisticLine l1, StatisticLine l2)
                    -> l2.getUserVisits().compareTo(l1.getUserVisits()));
            model.put("statisticList", list);
        }
        if(sortValue.equals("visitorVisits")){
            List<StatisticLine> list = statisticLineDAO.getAll();
            Collections.sort(list,(StatisticLine l1, StatisticLine l2)
                    -> l2.getVisitorVisits().compareTo(l1.getVisitorVisits()));
            model.put("statisticList", list);
        }
        if(sortValue.equals("avgRate")){
            List<StatisticLine> list = statisticLineDAO.getAll();
            Collections.sort(list,(StatisticLine l1, StatisticLine l2)
                    -> l2.getAvgRate().compareTo(l1.getAvgRate()));
            model.put("statisticList", list);
        }
        return model;
    }
}
