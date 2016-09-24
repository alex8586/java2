package lv.javaguru.java2.businesslogic.admin;

import lv.javaguru.java2.crossdomain.StatisticLine;
import lv.javaguru.java2.database.StatisticLineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticLineDAO statisticLineDAO;

    public Map<String, Object> model(){
        Map<String, Object> model = new HashMap<>();
        List<StatisticLine> list = statisticLineDAO.getAll();
        model.put("statisticList", list);
        return model;
    }
}
