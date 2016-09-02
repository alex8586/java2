package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.RecordIsNotAvailable;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.database.ShippingProfileDAO;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class ShippingProfileServiceImplTest {

    @Mock
    private ShippingProfileDAO shippingProfileDAO;
    @Mock
    private UserProvider userProvider;
    @InjectMocks
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
        User user = new User();
        user.setId(42);
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(user).when(userProvider).getUser();
        assertEquals(0, shippingProfileService.list().size());
    }

    @Test
    public void providedByDAOListWhenFine() throws ServiceException {
        List<ShippingProfile> profiles = new ArrayList<ShippingProfile>();
        profiles.add(Mockito.mock(ShippingProfile.class));
        profiles.add(Mockito.mock(ShippingProfile.class));

        User user = new User();
        user.setId(42);
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(user).when(userProvider).getUser();
        Mockito.doReturn(profiles).when(shippingProfileDAO).getAllByUser(user);
        assertEquals(profiles, shippingProfileService.list());
    }


    @Test(expected = IllegalRequestException.class)
    public void saveFailsWhenNoLoggedUser() throws ServiceException {
        Mockito.doReturn(false).when(userProvider).authorized();
        shippingProfileService.save(null);
    }

    @Test(expected = NullPointerException.class)
    public void saveFailsWhenFieldsContainsNulls() throws ServiceException {
        ShippingProfile shippingProfile = new ShippingProfile();
        Mockito.doReturn(true).when(userProvider).authorized();
        shippingProfileService.save(shippingProfile);
    }

    @Test(expected = WrongFieldFormatException.class)
    public void saveFailsWhenFieldsAreEmpty() throws ServiceException {
        ShippingProfile shippingProfile = record("", 0);
        Mockito.doReturn(true).when(userProvider).authorized();
        shippingProfileService.save(shippingProfile);
    }

    @Test
    public void saveCreateWhenFineRecordWithoutIdSupplied() throws ServiceException {
        ShippingProfile shippingProfile = record("data", 0);
        setupUserProviderMockWithUser(42);
        Mockito.doReturn(null).when(shippingProfileDAO).getById(shippingProfile.getId());
        shippingProfileService.save(shippingProfile);

        assertEquals(42, shippingProfile.getUserId());
        verify(shippingProfileDAO, times(1)).create(shippingProfile);
    }


    @Test(expected = RecordIsNotAvailable.class)
    public void saveFailsWhenNothingToUpdate() throws ServiceException {
        ShippingProfile shippingProfile = record("data", 1234);
        setupUserProviderMockWithUser(42);
        Mockito.doReturn(null).when(shippingProfileDAO).getById(shippingProfile.getId());

        shippingProfileService.save(shippingProfile);
    }

    @Test(expected = IllegalRequestException.class)
    public void saveFailsWhenRecordUnderUpdateBelongsToOtherUser() throws ServiceException {
        ShippingProfile oldProfile = Mockito.mock(ShippingProfile.class);
        ShippingProfile shippingProfile = record("data", 1234);
        setupUserProviderMockWithUser(42);
        Mockito.doReturn(oldProfile).when(shippingProfileDAO).getById(shippingProfile.getId());
        Mockito.doReturn(41L).when(oldProfile).getUserId();
        shippingProfileService.save(shippingProfile);
    }

    @Test
    public void saveUpdateWhenFineRecordWithIdSupplied() throws ServiceException {
        ShippingProfile oldProfile = Mockito.mock(ShippingProfile.class);
        ShippingProfile shippingProfile = record("data", 1234);
        setupUserProviderMockWithUser(42);
        Mockito.doReturn(oldProfile).when(shippingProfileDAO).getById(1234);
        Mockito.doReturn(42L).when(oldProfile).getUserId();
        shippingProfileService.save(shippingProfile);

        verify(shippingProfileDAO, times(1)).update(shippingProfile);
    }

    @Test(expected = IllegalRequestException.class)
    public void deleteFailsWhenNoLoggedUser() throws ServiceException {
        Mockito.doReturn(false).when(userProvider).authorized();
        shippingProfileService.delete(null);
    }

    @Test(expected = RecordIsNotAvailable.class)
    public void deleteFailsWhenNoRecordFound() throws ServiceException {
        ShippingProfile shippingProfile = record("stuff", -1);
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(null).when(shippingProfileDAO).getById(-1);
        shippingProfileService.delete(shippingProfile);
    }

    @Test(expected = IllegalRequestException.class)
    public void deleteFailsWhenRecordBelongsToOtherUser() throws ServiceException {
        ShippingProfile oldProfile = record("stuff", 123);
        oldProfile.setUserId(41);
        ShippingProfile shippingProfile = record("stuff", 123);
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(oldProfile).when(shippingProfileDAO).getById(123);
        setupUserProviderMockWithUser(42);
        shippingProfileService.delete(shippingProfile);
    }

    @Test
    public void deleteDeletesWhenEverythingFine() throws ServiceException {
        ShippingProfile oldProfile = record("stuff", 123);
        oldProfile.setUserId(42);
        ShippingProfile shippingProfile = record("stuff", 123);
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(oldProfile).when(shippingProfileDAO).getById(123);
        setupUserProviderMockWithUser(42);
        shippingProfileService.delete(shippingProfile);

        verify(shippingProfileDAO, times(1)).getById(123L);
        verify(shippingProfileDAO, times(1)).delete(shippingProfile);
    }

    public void setupUserProviderMockWithUser(long id) {
        User user = new User();
        user.setId(id);
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(user).when(userProvider).getUser();
        Mockito.doReturn(true).when(userProvider).isCurrent(user.getId());
    }

    public ShippingProfile record(String data, long id) {
        ShippingProfile shippingProfile = new ShippingProfile();
        shippingProfile.setDocument(data);
        shippingProfile.setPerson(data);
        shippingProfile.setPhone(data);
        shippingProfile.setAddress(data);
        shippingProfile.setId(id);
        return shippingProfile;
    }


}