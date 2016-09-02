package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.CountUsersDAO;
import lv.javaguru.java2.database.hybernate.CountClassHelper;
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
public class CountUsersDAOImplTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private CountClassHelper countClassHelper;
    @Qualifier("JDBC_CountUsersDAO")
    @Autowired
    private CountUsersDAO countUsersDAO;

    private Random random = new Random();

    @Before
    public void delete(){
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void createTest(){
        long userId = countClassHelper.createUser();
        long categoryId = countClassHelper.createCategory();
        long productId = countClassHelper.createProduct(categoryId);

        CountUser countUser = new CountUser();
        countUser.setUserId(userId);
        countUser.setProductId(productId);
        countUser.setCounter(17);
        assertTrue(0 == countUser.getId());

        long idFromDAO = countUsersDAO.create(countUser);
        CountUser fromDAO = countUsersDAO.getById(idFromDAO);
        assertEquals(countUser.getUserId(), fromDAO.getUserId());
        assertEquals(countUser.getProductId(), fromDAO.getProductId());
        assertTrue(countUser.getCounter() == fromDAO.getCounter());
        assertNotNull(fromDAO.getId());
    }

    @Test
    public void updateTest(){
        List list = createCountUsersListWith15Records();
        CountUser countUser = (CountUser) list.get(random.nextInt(15));
        long id = countUser.getId();
        long userId = countUser.getUserId();
        long productId = countUser.getProductId();
        int counter = countUser.getCounter();

        countUser.setUserId(((CountUser) list.get(random.nextInt(15))).getUserId());
        countUser.setProductId(((CountUser) list.get(random.nextInt(15))).getProductId());
        countUser.setCounter(7);
        countUsersDAO.update(countUser);
        assertTrue(id == countUser.getId());
        assertTrue(userId != countUser.getUserId());
        assertTrue(productId != countUser.getProductId());
        assertTrue(countUser.getCounter() == 7);
        assertNotSame(counter, countUser.getCounter());
    }

    @Test
    public void deleteTest(){
        CountUser countUser = createCountUser();
        long id = countUser.getId();

        countUsersDAO.delete(countUser);
        assertNull(countUsersDAO.getById(id));
    }

    @Test
    public void getByIdTest(){
        CountUser countUser = createCountUser();
        long id = countUser.getId();

        CountUser fromDAO = countUsersDAO.getById(id);
        assertTrue(countUser.getId() == fromDAO.getId());
        assertTrue(countUser.getUserId() == fromDAO.getUserId());
        assertTrue(countUser.getProductId() == fromDAO.getProductId());
        assertTrue(countUser.getCounter() == fromDAO.getCounter());

        CountUser other = createCountUser();
        long otherId = other.getId();
        CountUser otherFromDAO = countUsersDAO.getById(otherId);
        assertTrue(fromDAO.getId() != otherFromDAO.getId());
    }

    @Test
    public void getCountByProductIdTest(){
        List list = createCountUsersListWith15Records();
        CountUser countUser1 = (CountUser) list.get(8);
        CountUser countUser2 = (CountUser) list.get(3);

        int count1 = countUser1.getCounter();
        long productId1 = countUser1.getProductId();
        int count2 = countUser2.getCounter();
        long productId2 = countUser2.getProductId();

        assertTrue(count1 == countUsersDAO.getCountByProductId(productId1));
        assertTrue(count2 == countUsersDAO.getCountByProductId(productId2));
    }

    @Test
    public void getCountByUserIdTest(){
        List list = createCountUsersListWith15Records();
        CountUser countUser1 = (CountUser) list.get(random.nextInt(15));
        CountUser countUser2 = (CountUser) list.get(random.nextInt(15));

        int count1 = countUser1.getCounter();
        long userId1 = countUser1.getUserId();
        int count2 = countUser2.getCounter();
        long userId2 = countUser2.getUserId();

        assertTrue(count1 == countUsersDAO.getCountByUserId(userId1));
        assertTrue(count2 == countUsersDAO.getCountByUserId(userId2));
    }

    @Test
    public void getCountByProductIdAndUserIdTest(){
        List list = createCountUsersListWith15Records();
        CountUser countUser1 = (CountUser) list.get(random.nextInt(15));
        CountUser countUser2 = (CountUser) list.get(random.nextInt(15));

        int count1 = countUser1.getCounter();
        long productId1 = countUser1.getProductId();
        long userId1 = countUser1.getUserId();
        int count2 = countUser2.getCounter();
        long productId2 = countUser2.getProductId();
        long userId2 = countUser2.getUserId();

        assertTrue(count1 == countUsersDAO.getCountByProductIdAndUserId(productId1, userId1));
        assertTrue(count2 == countUsersDAO.getCountByProductIdAndUserId(productId2, userId2));
    }

    @Test
    public void getAllCountTest(){
        List list = createCountUsersListWith15Records();
        List fromDAO = countUsersDAO.getAllCount();
        assertTrue(list.size() == fromDAO.size());
    }

    public CountUser createCountUser(){
        long categoryId = countClassHelper.createCategory();
        long productId = countClassHelper.createProduct(categoryId);
        long userId = countClassHelper.createUser();

        CountUser countUser = new CountUser();
        countUser.setUserId(userId);
        countUser.setProductId(productId);
        countUser.setCounter(random.nextInt(30000));
        long id = countUsersDAO.create(countUser);
        return countUsersDAO.getById(id);
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
