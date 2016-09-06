package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ShippingProfileDAOImplTest extends CrudJdbcDAOTest<ShippingProfile, ShippingProfileDAOImpl> {

    @Autowired
    @Qualifier("JDBC_UserDAO")
    UserDAO userDAO;
    private User user = new User();

    @Before
    @Override
    public void before() {
        cleaner.cleanDatabase();
        user.setFullName("name");
        user.setEmail("email");
        user.setPassword("pass");
        userDAO.create(user);
        super.before();
    }


    @Override
    protected void compareRecords(ShippingProfile shippingProfile1, ShippingProfile shippingProfile2) {
        assertEquals(shippingProfile1.getId(), shippingProfile2.getId());
        assertEquals(shippingProfile1.getUserId(), shippingProfile2.getUserId());
        assertEquals(shippingProfile1.getPerson(), shippingProfile2.getPerson());
        assertEquals(shippingProfile1.getPhone(), shippingProfile2.getPhone());
        assertEquals(shippingProfile1.getDocument(), shippingProfile2.getDocument());
        assertEquals(shippingProfile1.getAddress(), shippingProfile2.getAddress());
    }

    @Override
    protected void fillRecordWithData(ShippingProfile shippingProfile) {
        shippingProfile.setUserId(user.getId());
        shippingProfile.setPhone("12345678-" + random.nextInt(100000));
        shippingProfile.setDocument("docno " + random.nextInt(100000));
        shippingProfile.setPerson("name surname " + random.nextInt(100000));
        shippingProfile.setAddress("city street house no " + random.nextInt(100000));
    }

    @Override
    protected void makeChangesInRecord(ShippingProfile shippingProfile) {
        shippingProfile.setAddress("another " + shippingProfile.getAddress());
        shippingProfile.setPhone("543234-" + random.nextInt(100000));
        shippingProfile.setDocument("another " + shippingProfile.getDocument());
        shippingProfile.setPerson("another person " + shippingProfile.getPhone());
    }

    @Test(expected = DBException.class)
    public void unableToCreateWithoutUser() {
        newRecord.setUserId(-1);
        newRecord.setId(0);
        dao.create(newRecord);
    }

    @Test(expected = DBException.class)
    public void unableToUpdateWithoutUser() {
        System.out.println(recordFromDAO);
        recordFromDAO.setUserId(-123);
        dao.update(recordFromDAO);
        System.out.println(recordFromDAO);
    }

    @Test
    public void profilesAreRemovedWithUser() {
        assertTrue(dao.getAll().size() > 0);
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.delete(user);
        assertEquals(0, user.getId());
        assertEquals(0, dao.getAll().size());
    }

    @Test(expected = DBException.class)
    public void unableToSaveWithNullFields() {
        recordFromDAO.setPerson(null);
        recordFromDAO.setDocument(null);
        dao.update(recordFromDAO);
    }

    @Test
    public void getAllByUserTest() {
        assertEquals(1, dao.getAllByUser(user).size());
        ShippingProfile shippingProfile = new ShippingProfile();
        fillRecordWithData(shippingProfile);
        dao.create(shippingProfile);
        assertEquals(2, dao.getAllByUser(user).size());
    }
}