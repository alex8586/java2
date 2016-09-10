package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.RecordIsNotAvailable;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.validators.ShippingDetailsFormatValidationService;
import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.ShippingProfileDAO;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.ShippingDetails;
import lv.javaguru.java2.dto.builders.ShippingDetailsUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class ShippingProfileServiceImplTest {

    @Mock
    ShippingDetails shippingDetails;
    @Mock
    ShippingProfile shippingProfile;
    @Mock
    User user;

    @Mock
    private ShippingProfileDAO shippingProfileDAO;
    @Mock
    private UserProvider userProvider;
    @Mock
    private ShippingDetailsFormatValidationService shippingDetailsFormatValidationService;
    @Spy
    private ShippingDetailsUtil shippingDetailsUtil;

    @InjectMocks
    @Autowired
    private ShippingProfileServiceImpl shippingProfileService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IllegalRequestException.class)
    public void listFailsWhenUserNotAuthorized() throws ServiceException {
        Mockito.doReturn(false).when(userProvider).authorized();
        shippingProfileService.list();
    }

    @Test
    public void emptyListWhenUserHaveNoProfiles() throws ServiceException {
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(user).when(userProvider).getUser();
        assertEquals(0, shippingProfileService.list().size());
    }

    @Test
    public void providedByDAOListWhenFine() throws ServiceException {
        List<ShippingProfile> profiles = new ArrayList<ShippingProfile>();
        profiles.add(shippingProfile);
        profiles.add(shippingProfile);

        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(user).when(userProvider).getUser();
        Mockito.doReturn(profiles).when(shippingProfileDAO).getAllByUser(user);
        assertEquals(profiles, shippingProfileService.list());
    }


    @Test(expected = IllegalRequestException.class)
    public void saveFailsWhenNoLoggedUser() throws ServiceException {
        Mockito.doReturn(false).when(userProvider).authorized();
        shippingProfileService.save(shippingDetails);
    }

    @Test(expected = IllegalMonitorStateException.class)
    public void saveFailForwardExceptionFromValidator() throws ServiceException {
        Exception exception = Mockito.mock(IllegalMonitorStateException.class);
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doThrow(exception).when(shippingDetailsFormatValidationService).validate(shippingDetails);
        shippingProfileService.save(shippingDetails);
    }

    @Test
    public void saveCreateWhenFineRecordWithoutIdSupplied() throws ServiceException {
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(user).when(userProvider).getUser();
        Mockito.doReturn(0L).when(shippingDetails).getId();
        Mockito.doReturn(shippingProfile).when(shippingDetailsUtil).buildShippingProfile(shippingDetails);
        assertEquals(shippingProfile, shippingProfileService.save(shippingDetails));
        verify(shippingProfileDAO, times(1)).create(shippingProfile);
    }


    @Test(expected = RecordIsNotAvailable.class)
    public void saveFailsWhenNothingToUpdate() throws ServiceException {
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(123L).when(shippingDetails).getId();
        Mockito.doReturn(null).when(shippingProfileDAO).getById(123L);
        shippingProfileService.save(shippingDetails);
    }


    @Test(expected = IllegalRequestException.class)
    public void saveFailsWhenRecordUnderUpdateBelongsToOtherUser() throws ServiceException {
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(123L).when(shippingDetails).getId();

        Mockito.doReturn(shippingProfile).when(shippingProfileDAO).getById(123L);
        Mockito.doReturn(41L).when(shippingProfile).getUserId();
        Mockito.doReturn(false).when(userProvider).isCurrent(41L);
        shippingProfileService.save(shippingDetails);
    }


    @Test
    public void saveUpdateWhenFineRecordWithIdSupplied() throws ServiceException {
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(true).when(userProvider).isCurrent(41L);
        Mockito.doReturn(user).when(userProvider).getUser();
        Mockito.doReturn(41L).when(user).getId();

        Mockito.doReturn(shippingProfile).when(shippingProfileDAO).getById(123L);
        Mockito.doReturn(123L).when(shippingDetails).getId();
        Mockito.doReturn(41L).when(shippingProfile).getUserId();

        shippingProfileService.save(shippingDetails);
        verify(shippingProfileDAO, times(1)).update(shippingProfile);
        verify(shippingDetailsUtil, times(1)).updateShippingProfile(shippingDetails, shippingProfile);
    }

    @Test(expected = IllegalRequestException.class)
    public void deleteFailsWhenNoLoggedUser() throws ServiceException {
        Mockito.doReturn(false).when(userProvider).authorized();
        shippingProfileService.delete(123L);
    }

    @Test(expected = RecordIsNotAvailable.class)
    public void deleteFailsWhenNoRecordFound() throws ServiceException {
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(null).when(shippingProfileDAO).getById(-1);
        shippingProfileService.delete(123L);
    }

    @Test(expected = IllegalRequestException.class)
    public void deleteFailsWhenRecordBelongsToOtherUser() throws ServiceException {
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(shippingProfile).when(shippingProfileDAO).getById(123L);
        Mockito.doReturn(41L).when(shippingProfile).getUserId();
        Mockito.doReturn(false).when(userProvider).isCurrent(41L);
        shippingProfileService.delete(123L);
    }


    @Test
    public void deleteDeletesWhenEverythingFine() throws ServiceException {

        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(shippingProfile).when(shippingProfileDAO).getById(123L);
        Mockito.doReturn(41L).when(shippingProfile).getUserId();
        Mockito.doReturn(true).when(userProvider).isCurrent(41L);
        shippingProfileService.delete(123L);
        verify(shippingProfileDAO, times(1)).delete(shippingProfile);
    }
}