package lv.javaguru.java2.businesslogic.product;

public interface RateService {

    void rate(int number);

    double getAverageRate(long productId);

    String getRateColor(double number);
}
