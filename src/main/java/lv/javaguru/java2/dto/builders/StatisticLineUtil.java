package lv.javaguru.java2.dto.builders;

import lv.javaguru.java2.businesslogic.product.RateService;
import lv.javaguru.java2.businesslogic.product.StatisticCountService;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Rate;
import lv.javaguru.java2.dto.StatisticLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticLineUtil {

    @Autowired
    private StatisticCountService statisticCountService;
    @Autowired
    private RateService rateService;

    public StatisticLine build(Product product){
        StatisticLine statisticLine = new StatisticLine();
        statisticLine.setProductId(product.getId());
        statisticLine.setProductName(product.getName());
        statisticLine.setCategoryId(product.getCategoryID());
        statisticLine.setCategoryName("name");
        statisticLine.setQuantityComments(product.getReviews().size());
        statisticLine.setRating(getRate(product));
        statisticLine.setViews(statisticCountService.getProductViews(product.getId()));
        return statisticLine;
    }

    public double getRate(Product product){
        List<Rate> list = product.getRates();
        return rateService.getAverageRate(list);
    }
}
