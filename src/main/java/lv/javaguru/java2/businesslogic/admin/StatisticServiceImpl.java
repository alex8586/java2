package lv.javaguru.java2.businesslogic.admin;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.utils.SortingService;
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
    @Autowired
    private TemplateService templateService;
    @Autowired
    private SortingService<StatisticLine> statisticLineSortingService;

    @Override
    public Map<String, Object> model() {
        return model(statisticLineSortingService.getDefaultSortingStrategy());
    }

    @Override
    public Map<String, Object> model(String sortBy) {
        Map<String, Object> model = new HashMap<>();
        List<StatisticLine> list = statisticLineDAO.getAll();
        statisticLineSortingService.sort(sortBy, list);
        model.put("sortingStrategies", statisticLineSortingService.sortingStrategies());
        model.put("statisticList", list);
        model.putAll(templateService.model());
        return model;
    }
}
