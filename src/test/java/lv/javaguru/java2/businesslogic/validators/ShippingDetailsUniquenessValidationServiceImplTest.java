package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.ShippingProfileDAO;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.ShippingDetails;
import lv.javaguru.java2.dto.builders.ShippingDetailsUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;


public class ShippingDetailsUniquenessValidationServiceImplTest {

    @Mock
    ShippingProfileDAO shippingProfileDAO;

    @Mock
    ShippingDetailsUtil shippingDetailsUtil;

    @Mock
    User user;
    @Mock
    ShippingDetails shippingDetails;
    @Mock
    ShippingProfile shippingProfile;

    @InjectMocks
    ShippingDetailsUniquenessValidationServiceImpl shippingDetailsUniquenessValidationService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void successWhenUserHaveNoProfiles() throws ServiceException {
        Mockito.doReturn(new ArrayList<ShippingProfile>()).when(shippingProfileDAO).getAllByUser(user);
        shippingDetailsUniquenessValidationService.validate(shippingDetails, user);
        Mockito.verify(shippingDetailsUtil, times(0)).buildShippingProfile(shippingDetails);
    }

    @Test(expected = ServiceException.class)
    public void throwsWhenEqualProfileExist() throws ServiceException {
        List<ShippingProfile> list = new ArrayList<>();
        list.add(shippingProfile);
        Mockito.doReturn(list).when(shippingProfileDAO).getAllByUser(user);
        Mockito.doReturn(shippingProfile).when(shippingDetailsUtil).buildShippingProfile(shippingDetails);
        shippingDetailsUniquenessValidationService.validate(shippingDetails, user);
    }

    @Test
    public void successWhenNoMatchExist() throws ServiceException {
        List<ShippingProfile> list = new ArrayList<>();
        list.add(Mockito.mock(ShippingProfile.class));
        Mockito.doReturn(list).when(shippingProfileDAO).getAllByUser(user);
        Mockito.doReturn(shippingProfile).when(shippingDetailsUtil).buildShippingProfile(shippingDetails);
        shippingDetailsUniquenessValidationService.validate(shippingDetails, user);
    }

}