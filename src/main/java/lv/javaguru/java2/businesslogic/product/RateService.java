package lv.javaguru.java2.businesslogic.product;

public interface RateService {

    void rate(int number) throws IllegalArgumentException;

    double getAverageRate(long productId);

    String getRateColor(double number);
}
