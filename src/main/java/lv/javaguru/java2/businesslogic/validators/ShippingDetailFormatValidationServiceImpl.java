package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.domain.ShippingProfile;
import org.springframework.stereotype.Component;

@Component
public class ShippingDetailFormatValidationServiceImpl implements ShippingDetailFormatValidationService {
    private final String EMPTY_FIELDS = "All fields must be filled";

    public boolean validate(String address, String person, String phone, String document) throws ServiceException {
        if (address == null || person == null || phone == null || document == null) {
            throw new NullPointerException();
        }
        if (address.isEmpty() || person.isEmpty() || phone.isEmpty() || document.isEmpty()) {
            throw new WrongFieldFormatException(EMPTY_FIELDS);
        }
        return true;
    }

    public boolean validate(ShippingProfile shippingProfile) throws ServiceException {
        return validate(shippingProfile.getAddress(),
                shippingProfile.getPerson(),
                shippingProfile.getPhone(),
                shippingProfile.getDocument());
    }

}
