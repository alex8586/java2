package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.dto.ShippingDetails;
import org.junit.Before;
import org.junit.Test;


public class ShippingDetailsFormatValidationServiceImplTest {

    ShippingDetailsFormatValidationServiceImpl shippingDetailsFormatValidationService;

    @Before
    public void before() {
        shippingDetailsFormatValidationService = new ShippingDetailsFormatValidationServiceImpl();
    }

    @Test(expected = WrongFieldFormatException.class)
    public void failsWhenThereAreEmptyFields() throws ServiceException {
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setAddress("");
        shippingDetails.setPhone("");
        shippingDetails.setDocument("");
        shippingDetails.setPerson("");
        shippingDetailsFormatValidationService.validate(shippingDetails);
    }

    @Test
    public void successWhenFieldsAreFilled() throws ServiceException {
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setAddress("A");
        shippingDetails.setPhone("A");
        shippingDetails.setDocument("A");
        shippingDetails.setPerson("A");
        shippingDetailsFormatValidationService.validate(shippingDetails);
    }
}