package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.BaseEntityTest;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserDAOImplTest extends BaseEntityTest<User, UserDAOImpl> {

    @Before
    @Override
    public void before() {
        cleaner.cleanDatabase();
        super.before();
    }

    @Override
    protected User newRecord() {
        return new User();
    }

    @Override
    protected void compareRecords(User user1, User user2) {
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getFullName(), user2.getFullName());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getPassword(), user2.getPassword());
    }

    @Override
    protected void fillRecordWithData(User user) {
        user.setEmail("a@b" + random.nextInt(100000) + ".com");
        user.setFullName("Name Surname " + random.nextInt(100000));
        user.setPassword("pass" + random.nextInt(100000));
    }

    @Override
    protected void makeChangesInRecord(User user) {
        user.setEmail("a." + user.getEmail());
        user.setFullName("Dr " + user.getFullName());
        user.setPassword("meow" + user.getPassword());
    }

    @Test(expected = DBException.class)
    public void createSecondUserWithSameEmailTest() {
        User user = new User();
        fillRecordWithData(user);
        user.setEmail(recordFromDAO.getEmail());
        dao.create(user);
    }

    @Test(expected = DBException.class)
    public void updateEmailToMatchAnotherEmailTest() {
        User user = new User();
        fillRecordWithData(user);
        user.setEmail("unique@ynuque.com");
        dao.create(user);
        recordFromDAO.setEmail(user.getEmail());
        dao.update(recordFromDAO);
    }

    @Test
    public void getByEmailWorksWithightId() {
        User user = dao.getByEmail(recordFromDAO.getEmail());
        compareRecords(recordFromDAO, user);
    }

    @Test
    public void findWithWrongEmailReturnsNull() {
        assertNull(dao.getByEmail("bad.bad@mail.com"));
    }

    @Test(expected = DBException.class)
    public void updatingWithNullFieldsFails() {
        recordFromDAO.setFullName(null);
        dao.update(recordFromDAO);
    }

}