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
        CountUser countUser1 = createCountUser();
        CountUser countUser2 = createCountUser();
        long id = countUser1.getId();
        long userId = countUser1.getUserId();
        long productId = countUser1.getProductId();
        int counter = countUser1.getCounter();

        countUser1.setUserId(countUser2.getUserId());
        countUser1.setProductId(countUser2.getProductId());
        countUser1.setCounter(countUser2.getCounter());

        countUsersDAO.update(countUser1);
        assertTrue(id == countUser1.getId());
        assertTrue(userId != countUser1.getUserId());
        assertTrue(productId != countUser1.getProductId());
        assertTrue(counter != countUser1.getCounter());
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
        CountUser countUser1 = (CountUser) list.get(random.nextInt(15));
        CountUser countUser2 = (CountUser) list.get(random.nextInt(15));
        CountUser countUser3 = (CountUser) list.get(random.nextInt(15));

        long productId = countUser1.getProductId();
        countUser2.setProductId(productId);
        countUsersDAO.update(countUser2);
        countUser3.setProductId(productId);
        countUsersDAO.update(countUser3);
        int count1 = countUser1.getCounter();
        int count2 = countUser2.getCounter();
        int count3 = countUser3.getCounter();

        int total = count1 + count2 + count3;
        assertTrue(total == countUsersDAO.getCountByProductId(productId));
    }

    @Test
    public void getCountByUserIdTest(){
        List list = createCountUsersListWith15Records();
        CountUser countUser1 = (CountUser) list.get(random.nextInt(15));
        CountUser countUser2 = (CountUser) list.get(random.nextInt(15));
        CountUser countUser3 = (CountUser) list.get(random.nextInt(15));

        long userId = countUser1.getUserId();
        countUser2.setUserId(userId);
        countUsersDAO.update(countUser2);
        countUser3.setUserId(userId);
        countUsersDAO.update(countUser3);
        int count1 = countUser1.getCounter();
        int count2 = countUser2.getCounter();
        int count3 = countUser3.getCounter();

        int total = count1 + count2 + count3;
        assertTrue(total == countUsersDAO.getCountByUserId(userId));
    }

    @Test
    public void getSumCountFromAllTableTest(){
        List<CountUser> list = createCountUsersListWith15Records();
        int total = 0;
        for(CountUser countUser : list){
            total += countUser.getCounter();
        }
        assertTrue(total == countUsersDAO.getSumCountFromAllTable());
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
