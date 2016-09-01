package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.domain.CountCustomer;
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
public class CountCustomerORMDAOImplTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private CountClassHelper countClassHelper;
    @Qualifier("ORM_CountCustomersDAO")
    @Autowired
    private CountCustomersORMDAOImpl countCustomersORMDAO;

    @Before
    public void before(){
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void createCountCustomerTest(){
        long categoryId = countClassHelper.createCategory();
        long productId =  countClassHelper.createProduct(categoryId);
        long userId = countClassHelper.createUser();

        CountCustomer countCustomer = new CountCustomer();
        countCustomer.setUserId(userId);
        countCustomer.setProductId(productId);
        countCustomer.setCounter(0);
        long countCustomerId = countCustomersORMDAO.create(countCustomer);

        assertTrue(countCustomerId > 0);
        CountCustomer customerFromDAO = countCustomersORMDAO.getById(countCustomerId);
        assertEquals(countCustomer.getUserId(), customerFromDAO.getUserId());
        assertEquals(countCustomer.getCounter(), customerFromDAO.getCounter());
        assertEquals(countCustomer.getProductId(), customerFromDAO.getProductId());
    }

    @Test
    public void updateCountCustomerTest(){
        CountCustomer countCustomer = createCountCustomer();
        int counter = countCustomer.getCounter();

        countCustomer.setCounter(7);
        countCustomersORMDAO.update(countCustomer);
        assertTrue(countCustomer.getCounter() == 7);
        assertNotSame(counter, countCustomer.getCounter());
    }

    @Test
    public void getCountByProductTest(){
        List list = createCountCustomersListWith15Records();
        CountCustomer countCustomer1 = (CountCustomer) list.get(8);
        CountCustomer countCustomer2 = (CountCustomer) list.get(3);

        int count1 = countCustomer1.getCounter();
        long productId1 = countCustomer1.getProductId();
        int count2 = countCustomer2.getCounter();
        long productId2 = countCustomer2.getProductId();

        assertTrue(count1 == countCustomersORMDAO.getCountByProduct(productId1));
        assertTrue(count2 == countCustomersORMDAO.getCountByProduct(productId2));
    }

    @Test
    public void getCountByCustomerTest(){
        List list = createCountCustomersListWith15Records();
        CountCustomer countCustomer1 = (CountCustomer) list.get(8);
        CountCustomer countCustomer2 = (CountCustomer) list.get(3);

        int count1 = countCustomer1.getCounter();
        long userId1 = countCustomer1.getUserId();
        int count2 = countCustomer2.getCounter();
        long userId2= countCustomer2.getUserId();

        assertTrue(count1 == countCustomersORMDAO.getCountByCustomer(userId1));
        assertTrue(count2 == countCustomersORMDAO.getCountByCustomer(userId2));
    }

    @Test
    public void getCountByProductAndVisitorTest(){

    }

    @Test
    public void getAllCountTest(){
        List list = createCountCustomersListWith15Records();
        assertTrue(list.size() == 15);
    }

    public CountCustomer createCountCustomer(){
        Random random = new Random();
        long categoryId = countClassHelper.createCategory();
        long productId =  countClassHelper.createProduct(categoryId);
        long userId = countClassHelper.createUser();

        CountCustomer countCustomer = new CountCustomer();
        countCustomer.setUserId(userId);
        countCustomer.setProductId(productId);
        countCustomer.setCounter(random.nextInt(30000));
        long countCustomerId = countCustomersORMDAO.create(countCustomer);
        return countCustomersORMDAO.getById(countCustomerId);
    }

    public List createCountCustomersListWith15Records(){
        List<CountCustomer> list = new LinkedList<>();
        for(int i = 0; i < 15; i++){
            CountCustomer countCustomer = createCountCustomer();
            int co = countCustomer.getCounter();
            System.out.println("------------------" + co);
            list.add(countCustomer);
        }
        return list;
    }
}
