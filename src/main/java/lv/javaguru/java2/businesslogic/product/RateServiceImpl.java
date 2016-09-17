package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.database.RateDAO;
import lv.javaguru.java2.domain.Rate;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RateServiceImpl implements RateService {

    @Autowired
    private RateDAO rateDAO;

    @Override
    public void rate(long productId, User user, int number) {
        if(number < 1 || number > 5){
            throw new IllegalArgumentException();
        }
        User user = userProvider.getUser();
        Rate rate = new Rate();
        rate.setUserId(user.getId());
        rate.setProductId(productId);
        rate.setRate(number);
        rateDAO.create(rate);
    }

    @Override
    public double getAverageRate(long productId) {
        Double average ;
        try {
            average = rateDAO.getAverageRate(productId);
        } catch (NullPointerException e) {
            average = 0d;
        }
        return Math.round(average*100.0)/100.0;
    }

    @Override
    public String getRateColor(double number){
        String color = "";
        if((int)Math.round(number) == 0){
            color = "white";
        }
        if((int)Math.round(number) == 1){
            color = "#e3f2fd";
        }
        if((int)Math.round(number) == 2){
            color = "#bbdefb";
        }
        if((int)Math.round(number) == 3){
            color = "#90caf9";
        }
        if((int)Math.round(number) == 4){
            color = "#64b5f6";
        }
        if((int)Math.round(number) == 5){
            color = "#42a5f5";
        }
        return color;
    }
}
