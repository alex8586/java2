package lv.javaguru.java2.businesslogic.admin;

import java.util.Map;

public interface StatisticService {

    Map<String, Object> model();

    Map<String, Object> sortBy(String sortValue);
}
