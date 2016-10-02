package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.serviceexception.RecordIsNotAvailable;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.validators.RateFormatValidationService;
import lv.javaguru.java2.businesslogic.validators.RateValidationService;
import lv.javaguru.java2.database.RateDAO;
import lv.javaguru.java2.domain.Rate;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RateServiceImpl implements RateService {
    private static final String CAN_NOT_RATE = "You can rate one time per product";
    private static final String[] RATE_COLOR_CODES = new String[]{"white", "#e3f2fd", "#bbdefb", "#90caf9", "#64b5f6", "#42a5f5"};


    @Autowired
    private RateDAO rateDAO;
    @Autowired
    private RateFormatValidationService rateFormatValidationService;
    @Autowired
    private RateValidationService rateValidationService;

    @Override
    public void rate(long productId, User user, int rate) throws ServiceException {
        rateFormatValidationService.validate(rate);

        if (user == null || productId == 0)
            throw new RecordIsNotAvailable();

        if (!rateValidationService.canRate(user, productId))
            throw new ServiceException(CAN_NOT_RATE);

        Rate newRate = new Rate();
        newRate.setUserId(user.getId());
        newRate.setProductId(productId);
        newRate.setRate(rate);
        rateDAO.create(newRate);
    }

    @Override
    public double getAverageRate(List<Rate> rateList) {
        double sum = 0;
        for (Rate rate : rateList) {
            sum += rate.getRate();
        }
        return Math.round(sum * 100.0 / rateList.size()) / 100.0;
    }

    @Override
    public String getRateColor(double number){
        int rate = (int) Math.round(number);
        try {
            return RATE_COLOR_CODES[rate];
        } catch (Exception e) {
            return "";
        }
    }
}
