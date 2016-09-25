package lv.javaguru.java2.businesslogic.validators;


import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.ShippingDetails;

public interface ShippingDetailsUniquenessValidationService {
    void validate(ShippingDetails shippingDetails, User user) throws ServiceException;
}
