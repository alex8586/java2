package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.dto.ShippingDetails;
import org.springframework.stereotype.Component;

@Component
public class ShippingDetailsFormatValidationServiceImpl implements ShippingDetailsFormatValidationService {
    private final String EMPTY_FIELDS = "All fields must be filled";

    public void validate(ShippingDetails shippingDetails) throws ServiceException {
        if (shippingDetails.getPerson().isEmpty() || shippingDetails.getAddress().isEmpty() ||
                shippingDetails.getPhone().isEmpty() || shippingDetails.getDocument().isEmpty()) {
            throw new WrongFieldFormatException(EMPTY_FIELDS);
        }
    }
}
