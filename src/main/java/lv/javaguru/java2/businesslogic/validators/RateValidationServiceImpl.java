package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.database.RateDAO;
import lv.javaguru.java2.domain.Rate;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RateValidationServiceImpl implements RateValidationService {

    @Autowired
    private RateDAO rateDAO;

    @Override
    public boolean canRate(User user, long productId) {
        Rate existingRate = rateDAO.getByUserIdAndProductId(user.getId(), productId);
        return existingRate == null;
    }
}
