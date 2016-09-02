package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.ShippingProfile;

import java.util.List;

public interface ShippingProfileService {
    List<ShippingProfile> list() throws ServiceException;

    void save(ShippingProfile shippingProfile) throws ServiceException;

    void delete(ShippingProfile shippingProfile) throws ServiceException;
}
