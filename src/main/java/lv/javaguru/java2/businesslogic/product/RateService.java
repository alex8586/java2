package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.domain.User;

public interface RateService {

    void rate(long productId, User user, int number);

    double getAverageRate(long productId);

    String getRateColor(double number);
}
