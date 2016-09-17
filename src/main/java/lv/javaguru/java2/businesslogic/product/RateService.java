package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.Rate;
import lv.javaguru.java2.domain.User;

import java.util.List;

public interface RateService {

    void rate(long productId, User user, int number) throws ServiceException;

    double getAverageRate(List<Rate> rateList);

    String getRateColor(double number);
}
