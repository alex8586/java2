package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.dto.ShippingDetails;

import java.util.List;

public interface ShippingProfileService {
    List<ShippingProfile> list() throws ServiceException;

    ShippingProfile save(ShippingDetails shippingDetails) throws ServiceException;

    void delete(ShippingProfile shippingProfile) throws ServiceException;
}
