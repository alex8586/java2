package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.CountUsersDAO;
import lv.javaguru.java2.domain.CountUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class CountUserORMDAOImplTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private ObjectCreator objectCreator;

    @Qualifier("ORM_CountUsersDAO")
    @Autowired
    private CountUsersDAO countUsersORMDAO;

    private Random random = new Random();

    @Before
    public void before() {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void createCountUserTest() {
        long categoryId = objectCreator.createCategory();
        long productId = objectCreator.createProduct(categoryId);
        long userId = objectCreator.createUser();

        CountUser countUser = new CountUser();
        countUser.setUserId(userId);
        countUser.setProductId(productId);
        countUser.setCounter(0);
        long countUserId = countUsersORMDAO.create(countUser);

        assertTrue(countUserId > 0);
        CountUser customerFromDAO = countUsersORMDAO.getById(countUserId);
        assertEquals(countUser.getUserId(), customerFromDAO.getUserId());
        assertEquals(countUser.getCounter(), customerFromDAO.getCounter());
        assertEquals(countUser.getProductId(), customerFromDAO.getProductId());
    }

    @Test
    public void updateCountUserTest() {
        CountUser countUser1 = createCountUser();
        CountUser countUser2 = createCountUser();
        long id = countUser1.getId();
        long userId = countUser1.getUserId();
        long productId = countUser1.getProductId();
        long counter = countUser1.getCounter();

        countUser1.setUserId(countUser2.getUserId());
        countUser1.setProductId(countUser2.getProductId());
        countUser1.setCounter(countUser2.getCounter());

        countUsersORMDAO.update(countUser1);
        assertTrue(id == countUser1.getId());
        assertTrue(userId != countUser1.getUserId());
        assertTrue(productId != countUser1.getProductId());
        assertTrue(counter != countUser1.getCounter());
    }

    @Test
    public void deleteCountUserTest() {
        CountUser countUser = createCountUser();
        long id = countUser.getId();

        countUsersORMDAO.delete(countUser);
        assertNull(countUsersORMDAO.getById(id));
    }

    @Test
    public void getById() {
        CountUser countUser = createCountUser();
        long id = countUser.getId();

        CountUser fromDAO = countUsersORMDAO.getById(id);
        assertTrue(countUser.getId() == fromDAO.getId());
        assertTrue(countUser.getUserId() == fromDAO.getUserId());
        assertTrue(countUser.getProductId() == fromDAO.getProductId());
        assertTrue(countUser.getCounter() == fromDAO.getCounter());

        CountUser other = createCountUser();
        long otherId = other.getId();
        CountUser otherFromDAO = countUsersORMDAO.getById(otherId);
        assertTrue(fromDAO.getId() != otherFromDAO.getId());
    }

    @Test
    public void getCountByProductIdTest() {
        CountUser countUser1 = createCountUser();
        CountUser countUser2 = createCountUser();
        CountUser countUser3 = createCountUser();

        long productId = countUser1.getProductId();
        countUser2.setProductId(productId);
        countUsersORMDAO.update(countUser2);
        countUser3.setProductId(productId);
        countUsersORMDAO.update(countUser3);
        long count1 = countUser1.getCounter();
        long count2 = countUser2.getCounter();
        long count3 = countUser3.getCounter();

        long total = count1 + count2 + count3;
        assertEquals(total, countUsersORMDAO.getCountByProductId(productId));
    }

    @Test
    public void getCountByUserIdTest() {
        CountUser countUser1 = createCountUser();
        CountUser countUser2 = createCountUser();
        CountUser countUser3 = createCountUser();

        long userId = countUser1.getUserId();
        countUser2.setUserId(userId);
        countUsersORMDAO.update(countUser2);
        countUser3.setUserId(userId);
        countUsersORMDAO.update(countUser3);
        long count1 = countUser1.getCounter();
        long count2 = countUser2.getCounter();
        long count3 = countUser3.getCounter();

        long total = count1 + count2 + count3;
        assertEquals(total, countUsersORMDAO.getCountByUserId(userId));
    }

    @Test
    public void getSumCountFromAllTableTest() {
        List<CountUser> list = createCountUsersListWith15Records();
        int total = 0;
        for (CountUser countUser : list) {
            total += countUser.getCounter();
        }
        assertTrue(total == countUsersORMDAO.getSumCountFromAllTable());
    }

    @Test
    public void getAllCountTest() {
        List list = createCountUsersListWith15Records();
        List fromDAO = countUsersORMDAO.getAllCount();
        assertTrue(list.size() == fromDAO.size());
    }

    @Test
    public void getCountUserByUserIdProductIdTest() {
        CountUser countUser = createCountUser();
        long userId = countUser.getUserId();
        long productId = countUser.getProductId();

        CountUser fromDAO = countUsersORMDAO.getCountUserByUserIdProductId(userId, productId);
        assertNotNull(fromDAO);
        assertEquals(countUser, fromDAO);
    }

    public CountUser createCountUser() {
        long categoryId = objectCreator.createCategory();
        long productId = objectCreator.createProduct(categoryId);
        long userId = objectCreator.createUser();

        CountUser countUser = new CountUser();
        countUser.setUserId(userId);
        countUser.setProductId(productId);
        countUser.setCounter(random.nextInt(1000));
        long countUserId = countUsersORMDAO.create(countUser);
        return countUsersORMDAO.getById(countUserId);
    }

    public List createCountUsersListWith15Records() {
        List<CountUser> list = new LinkedList<>();
        for (int i = 0; i < 15; i++) {
            CountUser countUser = createCountUser();
            list.add(countUser);
        }
        return list;
    }
}
