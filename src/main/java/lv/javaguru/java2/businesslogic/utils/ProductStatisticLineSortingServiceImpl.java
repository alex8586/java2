package lv.javaguru.java2.businesslogic.utils;


import lv.javaguru.java2.crossdomain.StatisticLine;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class ProductStatisticLineSortingServiceImpl extends SortingService<StatisticLine> {

    public ProductStatisticLineSortingServiceImpl() {
        defaultSortingStrategy = "BYPRODUCTID";

        keyToKey.put("Product id", "BYPRODUCTID");
        keyToKey.put("Product name", "BYPRODUCTNAME");
        keyToKey.put("Category id", "BYCATEGORYID");
        keyToKey.put("Category name", "BYCATEGORYNAME");

        keyToKey.put("Comments", "BYCOMMENTCOUNT");
        keyToKey.put("User count", "BYREGISTREDVIEWS");
        keyToKey.put("Visitor count", "BYANONIMOUSVIEWS");
        keyToKey.put("Rate", "BYAVERAGERATING");

        comparators.put("BYPRODUCTID", Comparator.comparing(StatisticLine::getProductId));
        comparators.put("BYPRODUCTNAME", Comparator.comparing(StatisticLine::getProductName));
        comparators.put("BYCATEGORYID", Comparator.comparing(StatisticLine::getCategoryId));
        comparators.put("BYCATEGORYNAME", Comparator.comparing(StatisticLine::getCategoryName));
        comparators.put("BYCOMMENTCOUNT", Comparator.comparing(StatisticLine::getReviewCount));
        comparators.put("BYREGISTREDVIEWS", Comparator.comparing(StatisticLine::getUserVisits));
        comparators.put("BYANONIMOUSVIEWS", Comparator.comparing(StatisticLine::getVisitorVisits));
        comparators.put("BYAVERAGERATING", Comparator.comparing(StatisticLine::getAvgRate));
    }
}
