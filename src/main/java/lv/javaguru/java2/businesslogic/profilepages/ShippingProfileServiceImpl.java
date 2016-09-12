package lv.javaguru.java2.businesslogic.profilepages;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.RecordIsNotAvailable;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.businesslogic.validators.ShippingDetailsFormatValidationService;
import lv.javaguru.java2.database.ShippingProfileDAO;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.dto.ShippingDetails;
import lv.javaguru.java2.dto.builders.ShippingDetailsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ShippingProfileServiceImpl implements ShippingProfileService {

    @Autowired
    UserProvider userProvider;

    @Autowired
    @Qualifier("ORM_ShippingProfileDAO")
    ShippingProfileDAO shippingProfileDAO;

    @Autowired
    ShippingDetailsUtil shippingDetailsUtil;

    @Autowired
    ShippingDetailsFormatValidationService shippingDetailsFormatValidationService;

    @Override
    public List<ShippingProfile> list() throws ServiceException {
        if (!userProvider.authorized())
            throw new IllegalRequestException();
        Map<String, Object> map = new HashMap<String, Object>();
        List<ShippingProfile> shippingProfiles = shippingProfileDAO.getAllByUser(userProvider.getUser());
        return shippingProfiles;
    }

    @Override
    public ShippingProfile save(ShippingDetails shippingDetails) throws ServiceException {
        if (!userProvider.authorized())
            throw new IllegalRequestException();
        shippingDetailsFormatValidationService.validate(shippingDetails);

        if (shippingDetails.getId() > 0) {
            ShippingProfile oldProfile = shippingProfileDAO.getById(shippingDetails.getId());
            if (oldProfile == null)
                throw new RecordIsNotAvailable();
            if (!userProvider.isCurrent(oldProfile.getUserId()))
                throw new IllegalRequestException();
            shippingDetailsUtil.updateShippingProfile(shippingDetails, oldProfile);
            shippingProfileDAO.update(oldProfile);
            return oldProfile;
        } else {
            ShippingProfile shippingProfile = shippingDetailsUtil.buildShippingProfile(shippingDetails);
            shippingProfile.setUserId(userProvider.getUser().getId());
            shippingProfileDAO.create(shippingProfile);
            return shippingProfile;
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        if (!userProvider.authorized())
            throw new IllegalRequestException();

        ShippingProfile oldProfile = shippingProfileDAO.getById(id);
        if (oldProfile == null)
            throw new RecordIsNotAvailable();

        if (!userProvider.isCurrent(oldProfile.getUserId()))
            throw new IllegalRequestException();

        shippingProfileDAO.delete(oldProfile);
    }

    @Override
    public void delete(ShippingProfile shippingProfile) throws ServiceException {
        delete(shippingProfile.getId());
    }
}
