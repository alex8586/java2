package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.RecordIsNotAvailable;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.validators.ShippingDetailFormatValidationService;
import lv.javaguru.java2.database.ShippingProfileDAO;
import lv.javaguru.java2.domain.ShippingProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ShippingProfileServiceImpl implements ShippingProfileService {
    private final String EMPTY_FIELDS = "All fields must be filled";

    @Autowired
    UserProvider userProvider;

    @Autowired
    @Qualifier("ORM_ShippingProfileDAO")
    ShippingProfileDAO shippingProfileDAO;

    @Autowired
    ShippingDetailFormatValidationService shippingDetailFormatValidationService;

    @Override
    public List<ShippingProfile> list() throws ServiceException {
        if (!userProvider.authorized())
            throw new IllegalRequestException();
        Map<String, Object> map = new HashMap<String, Object>();
        List<ShippingProfile> shippingProfiles = shippingProfileDAO.getAllByUser(userProvider.getUser());
        return shippingProfiles;
    }

    @Override
    public void save(ShippingProfile shippingProfile) throws ServiceException {
        if (!userProvider.authorized())
            throw new IllegalRequestException();

        System.out.println(shippingDetailFormatValidationService);
        if (!shippingDetailFormatValidationService.validate(shippingProfile)) {
            throw new IllegalStateException();
        }

        if (shippingProfile.getId() > 0) {
            ShippingProfile oldProfile = shippingProfileDAO.getById(shippingProfile.getId());
            if (oldProfile == null)
                throw new RecordIsNotAvailable();
            if (!userProvider.isCurrent(oldProfile.getUserId()))
                throw new IllegalRequestException();
            shippingProfileDAO.update(shippingProfile);
        } else {
            shippingProfile.setUserId(userProvider.getUser().getId());
            shippingProfileDAO.create(shippingProfile);
        }
    }

    @Override
    public void delete(ShippingProfile shippingProfile) throws ServiceException {
        if (!userProvider.authorized())
            throw new IllegalRequestException();

        ShippingProfile oldProfile = shippingProfileDAO.getById(shippingProfile.getId());
        if (oldProfile == null)
            throw new RecordIsNotAvailable();

        if (!userProvider.isCurrent(oldProfile.getUserId()))
            throw new IllegalRequestException();

        shippingProfileDAO.delete(shippingProfile);
    }
}
