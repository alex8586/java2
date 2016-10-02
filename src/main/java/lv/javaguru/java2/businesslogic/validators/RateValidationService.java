package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.domain.User;


public interface RateValidationService {
    boolean canRate(User user, long productId);
}
