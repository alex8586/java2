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
    private CountClassHelper countClassHelper;
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
        long categoryId = countClassHelper.createCategory();
        long productId = countClassHelper.createProduct(categoryId);
        long userId = countClassHelper.createUser();

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
        List list = createCountUsersListWith15Records();
        CountUser countUser = (CountUser) list.get(random.nextInt(15));
        long id = countUser.getId();
        long userId = countUser.getUserId();
        long productId = countUser.getProductId();
        int counter = countUser.getCounter();

        countUser.setUserId(((CountUser) list.get(random.nextInt(15))).getUserId());
        countUser.setProductId(((CountUser) list.get(random.nextInt(15))).getProductId());
        countUser.setCounter(7);
        countUsersORMDAO.update(countUser);
        assertTrue(id == countUser.getId());
        assertTrue(userId != countUser.getUserId());
        assertTrue(productId != countUser.getProductId());
        assertTrue(countUser.getCounter() == 7);
        assertNotSame(counter, countUser.getCounter());
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
        List list = createCountUsersListWith15Records();
        CountUser countUser1 = (CountUser) list.get(random.nextInt(15));
        CountUser countUser2 = (CountUser) list.get(random.nextInt(15));
        CountUser countUser3 = (CountUser) list.get(random.nextInt(15));

        long productId = countUser1.getProductId();
        countUser2.setProductId(productId);
        countUsersORMDAO.update(countUser2);
        countUser3.setProductId(productId);
        countUsersORMDAO.update(countUser3);
        System.out.println("----------------");
        int count1 = countUser1.getCounter();
        System.out.println(count1+ "product id = "+countUser1.getProductId());
        int count2 = countUser2.getCounter();
        System.out.println(count2+ "product id = "+countUser2.getProductId());
        int count3 = countUser3.getCounter();
        System.out.println(count3+ "product id = "+countUser3.getProductId());

        int total = count1 + count2 + count3;
        System.out.println("total " + total);
        int fromdao = countUsersORMDAO.getCountByProductId(productId);
        System.out.println("from dao " + fromdao);
        assertTrue(total == countUsersORMDAO.getCountByProductId(productId));
    }

    @Test
    public void getCountByUserIdTest() {
        long categoryId = countClassHelper.createCategory();
        System.out.println("category created");
        long productId = countClassHelper.createProduct(categoryId);
        System.out.println("product created");
        long userId = countClassHelper.createUser();
        System.out.println("user created");

        CountUser countUser = new CountUser();
        countUser.setUserId(userId);
        countUser.setProductId(productId);
        countUser.setCounter(random.nextInt(30000));

        long categoryId2 = countClassHelper.createCategory();
        System.out.println("-------- second category created");
        long productId2 = countClassHelper.createProduct(categoryId);
        System.out.println("--------- second product created");
        CountUser countUser2 = new CountUser();
        countUser2.setUserId(userId);
        countUser.setProductId(productId2);
        countUser2.setCounter(random.nextInt(30000));
        countUsersORMDAO.create(countUser2);

        int total = countUser.getCounter() + countUser2.getCounter();
        assertTrue(total == countUsersORMDAO.getCountByUserId(userId));

    }

    @Test
    public void getCountByProductIdAndUserIdTest() {
        List list = createCountUsersListWith15Records();
        CountUser countUser1 = (CountUser) list.get(random.nextInt(15));
        CountUser countUser2 = (CountUser) list.get(random.nextInt(15));


        int count1 = countUser1.getCounter();
        long productId1 = countUser1.getProductId();
        long userId1 = countUser1.getUserId();
        int count2 = countUser2.getCounter();
        long productId2 = countUser2.getProductId();
        long userId2 = countUser2.getUserId();

        assertTrue(count1 == countUsersORMDAO.getSumCountFromAllTable());
        assertTrue(count2 == countUsersORMDAO.getSumCountFromAllTable());
    }

    @Test
    public void getAllCountTest() {
        List list = createCountUsersListWith15Records();
        List fromDAO = countUsersORMDAO.getAllCount();
        assertTrue(list.size() == fromDAO.size());
    }

    public CountUser createCountUser() {
        long categoryId = countClassHelper.createCategory();
        long productId = countClassHelper.createProduct(categoryId);
        long userId = countClassHelper.createUser();

        CountUser countUser = new CountUser();
        countUser.setUserId(userId);
        countUser.setProductId(productId);
        countUser.setCounter(random.nextInt(30000));
        long countCustomerId = countUsersORMDAO.create(countUser);
        return countUsersORMDAO.getById(countCustomerId);
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
