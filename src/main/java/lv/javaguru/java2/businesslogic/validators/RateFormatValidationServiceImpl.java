package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import org.springframework.stereotype.Component;

@Component
public class RateFormatValidationServiceImpl implements RateFormatValidationService {

    private static final int MINIMAL_RATE = 1;
    private static final int MAXIMAL_RATE = 5;

    @Override
    public void validate(int rate) throws ServiceException {
        if (rate < MINIMAL_RATE || rate > MAXIMAL_RATE)
            throw new IllegalRequestException();
    }
}
