package lv.javaguru.java2.database;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class UserDAOImplTest {

    private UserDAOImpl userDAO = new UserDAOImpl();
    private DatabaseCleaner cleaner = new DatabaseCleaner();

    @Before
    public void cleanDatabase() throws DBException {
        cleaner.cleanDatabase();
    }

    @Test
    public void createUserWithIdTest() throws DBException {
        User user = helperCreateOneUserWithoutId();
        assertEquals(0, user.getId());

        userDAO.create(user);
        assertNotNull(user.getId());

        User newUser = helperCreateOneUserWithoutId();
        userDAO.create(newUser);
        User first = userDAO.getById(user.getId());
        User second = userDAO.getById(newUser.getId());
        assertNotSame(first.getId(), second.getId());
    }

    @Test
    public void createUserReturnIdTest() throws DBException {
        User user = helperCreateOneUserWithoutId();
        assertEquals(0, user.getId());

        long id = userDAO.create(user);
        User userFromDao = userDAO.getById(id);
        assertTrue(id > 0);

        assertEquals(user.getFullName(), userFromDao.getFullName());
        assertEquals(user.getEmail(), userFromDao.getEmail());
        assertEquals(user.getPassword(), userFromDao.getPassword());
    }

    @Test
    public void updateUserTest() throws DBException {
        User user = helperCreateOneUserWithoutId();
        assertEquals(0, user.getId());
        userDAO.create(user);
        assertTrue(user.getId() > 0);

        User userFromDao = userDAO.getById(user.getId());
        userFromDao.setFullName("new Name");
        userFromDao.setEmail("mail@me.newMail");
        userFromDao.setPassword("new Password");

        userDAO.update(userFromDao);

        User updatedUser = userDAO.getById(userFromDao.getId());
        assertEquals(userFromDao.getId(), updatedUser.getId());
        assertEquals(updatedUser.getFullName(), "new Name");
        assertEquals(updatedUser.getEmail(), "mail@me.newMail");
        assertEquals(updatedUser.getPassword(), "new Password");
    }

    @Test
    public void deleteUserTest() throws DBException {
        User user = helperCreateOneUserWithoutId();
        userDAO.create(user);
        assertNotNull(user.getId());

        long id = user.getId();
        userDAO.delete(user);
        assertEquals(0, user.getId());

        User deletedUser = userDAO.getById(id);
        assertNull(deletedUser);
    }

    @Test
    public void getUserByIdTest() throws DBException {
        User user = helperCreateOneUserWithoutId();
        userDAO.create(user);
        long id = user.getId();

        User userFromDao = userDAO.getById(id);
        assertEquals(user.getId(), userFromDao.getId());
        assertEquals(user.getFullName(), userFromDao.getFullName());
        assertEquals(user.getEmail(), userFromDao.getEmail());
        assertEquals(user.getPassword(), userFromDao.getPassword());
    }

    @Test
    public void getAllUsersTest() throws DBException {
        User user = new User();
        user.setFullName("1");
        user.setEmail("1");
        user.setPassword("1");
        userDAO.create(user);

        User secondUser = new User();
        secondUser.setFullName("2");
        secondUser.setEmail("2");
        secondUser.setPassword("2");
        userDAO.create(secondUser);

        User thirdUser = new User();
        thirdUser.setFullName("3");
        thirdUser.setEmail("3");
        thirdUser.setPassword("3");
        userDAO.create(thirdUser);

        List<User> userList = userDAO.getAll();
        assertEquals(3, userList.size());

        userDAO.delete(thirdUser);
        userList.clear();
        userList = userDAO.getAll();
        assertEquals(2, userList.size());

        cleaner.cleanDatabase();
        userList.clear();
        userList = userDAO.getAll();
        assertEquals(0, userList.size());
    }

    private User helperCreateOneUserWithoutId(){
        User user = new User();
        user.setFullName("fullName");
        user.setEmail("mail@me.later");
        user.setPassword("password");
        return user;
    }

}