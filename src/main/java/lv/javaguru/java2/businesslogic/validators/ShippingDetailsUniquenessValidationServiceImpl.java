package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.ShippingProfileDAO;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.ShippingDetails;
import lv.javaguru.java2.dto.builders.ShippingDetailsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ShippingDetailsUniquenessValidationServiceImpl implements ShippingDetailsUniquenessValidationService {

    @Autowired
    @Qualifier("ORM_ShippingProfileDAO")
    ShippingProfileDAO shippingProfileDAO;

    @Autowired
    ShippingDetailsUtil shippingDetailsUtil;

    @Override
    public void validate(ShippingDetails shippingDetails, User user) throws ServiceException {
        List<ShippingProfile> shippingProfileList = shippingProfileDAO.getAllByUser(user);
        if (shippingProfileList.size() == 0)
            return;
        ShippingProfile shippingProfile = shippingDetailsUtil.buildShippingProfile(shippingDetails);
        shippingProfile.setUserId(user.getId());

        if (shippingProfileList.contains(shippingProfile))
            throw new ServiceException("Such shipping profile already exists");
    }
}
