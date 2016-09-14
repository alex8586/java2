package lv.javaguru.java2.businesslogic.profilepages;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.ShippingDetails;

import java.util.Map;

public interface ShippingProfileService {
    Map<String, Object> model() throws ServiceException;
    Map<String, Object> model(User user) throws ServiceException;

    ShippingProfile save(ShippingDetails shippingDetails) throws ServiceException;

    ShippingProfile save(ShippingDetails shippingDetails, User user) throws ServiceException;

    void delete(Long id) throws ServiceException;
    void delete(ShippingProfile shippingProfile) throws ServiceException;
}
