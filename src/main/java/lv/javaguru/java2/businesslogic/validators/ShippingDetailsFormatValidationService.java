package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.dto.ShippingDetails;

/**
 * Created by algis on 16.7.9.
 */
public interface ShippingDetailsFormatValidationService {
    void validate(ShippingDetails shippingDetails) throws ServiceException;
}
