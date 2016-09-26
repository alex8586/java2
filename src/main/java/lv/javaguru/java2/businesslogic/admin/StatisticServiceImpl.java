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

    private final static String PRODUCT_NAME = "getProductName()";

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
        List<StatisticLine> list = statisticLineDAO.getAll();

        if(sortValue.equals("productId")){
            Collections.sort(list, (StatisticLine l1, StatisticLine l2) ->
                    Long.valueOf(l1.getProductId()).compareTo(Long.valueOf(l2.getProductId())));
        }
        if(sortValue.equals("productName")){
            Collections.sort(list,(StatisticLine l1, StatisticLine l2)
                    -> l1.getProductName().compareTo(l2.getProductName()));
        }
        if(sortValue.equals("categoryId")){
            Collections.sort(list, (StatisticLine l1, StatisticLine l2) ->
                    Long.valueOf(l1.getCategoryId()).compareTo(Long.valueOf(l2.getCategoryId())));
        }
        if(sortValue.equals("categoryName")){
            Collections.sort(list,(StatisticLine l1, StatisticLine l2)
                    -> l1.getCategoryName().compareTo(l2.getCategoryName()));
        }
        if(sortValue.equals("reviewCount")){
            Collections.sort(list, (StatisticLine l1, StatisticLine l2) ->
                    Long.valueOf(l1.getReviewCount()).compareTo(Long.valueOf(l2.getReviewCount())));
        }
        if(sortValue.equals("userVisits")){
            Collections.sort(list,(StatisticLine l1, StatisticLine l2)
                    -> l2.getUserVisits().compareTo(l1.getUserVisits()));
        }
        if(sortValue.equals("visitorVisits")){
            Collections.sort(list,(StatisticLine l1, StatisticLine l2)
                    -> l2.getVisitorVisits().compareTo(l1.getVisitorVisits()));
        }
        if(sortValue.equals("avgRate")){
            Collections.sort(list,(StatisticLine l1, StatisticLine l2)
                    -> l2.getAvgRate().compareTo(l1.getAvgRate()));
        }
        model.put("statisticList", list);
        return model;
    }
}
