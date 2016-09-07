package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.ShippingProfile;

/**
 * Created by algis on 16.7.9.
 */
public interface ShippingDetailFormatValidationService {
    boolean validate(String address, String person, String phone, String document) throws ServiceException;

    boolean validate(ShippingProfile shippingProfile) throws ServiceException;
}
