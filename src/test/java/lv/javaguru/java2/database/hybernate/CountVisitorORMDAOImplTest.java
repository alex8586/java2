package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.CountVisitorsDAO;
import lv.javaguru.java2.domain.CountVisitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
@WebAppConfiguration
public class CountVisitorORMDAOImplTest {
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private ObjectCreator objectCreator;
    @Qualifier("ORM_CountVisitorsDAO")
    @Autowired
    private CountVisitorsDAO countVisitorsORMDAO;

    private Random random = new Random();

    @Before
    public void before() {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void createCountUserTest() {
        long categoryId = objectCreator.createCategory();
        long productId = objectCreator.createProduct(categoryId);

        CountVisitor countVisitor = new CountVisitor();
        countVisitor.setIp("111.222.333.444");
        countVisitor.setProductId(productId);
        countVisitor.setCounter(0);
        long countVisitorId = countVisitorsORMDAO.create(countVisitor);

        assertTrue(countVisitorId > 0);
        CountVisitor countVisitorFromDAO = countVisitorsORMDAO.getById(countVisitorId);
        assertEquals(countVisitor.getIp(), countVisitorFromDAO.getIp());
        assertEquals(countVisitor.getCounter(), countVisitorFromDAO.getCounter());
        assertEquals(countVisitor.getProductId(), countVisitorFromDAO.getProductId());
    }

    @Test
    public void updateCountUserTest() {
        CountVisitor countVisitor1 = createCountVisitorWithIp(34566);
        CountVisitor countVisitor2 = createCountVisitorWithIp(65432);

        long id = countVisitor1.getId();
        String ip = countVisitor1.getIp();
        long productId = countVisitor1.getProductId();
        long counter = countVisitor1.getCounter();

        countVisitor1.setIp(countVisitor2.getIp());
        countVisitor1.setProductId(countVisitor2.getProductId());
        countVisitor1.setCounter(countVisitor2.getCounter());

        countVisitorsORMDAO.update(countVisitor1);
        assertTrue(id == countVisitor1.getId());
        assertNotEquals(ip, countVisitor1.getIp());
        assertTrue(productId != countVisitor1.getProductId());
        assertTrue(counter != countVisitor1.getCounter());
    }

    @Test
    public void deleteCountUserTest() {
        CountVisitor countVisitor = createCountVisitorWithIp(8765);
        long id = countVisitor.getId();

        countVisitorsORMDAO.delete(countVisitor);
        assertNull(countVisitorsORMDAO.getById(id));
    }

    @Test
    public void getById() {
        CountVisitor countVisitor = createCountVisitorWithIp(4567);
        long id = countVisitor.getId();

        CountVisitor fromDAO = countVisitorsORMDAO.getById(id);
        assertTrue(countVisitor.getId() == fromDAO.getId());
        assertEquals(countVisitor.getIp(), fromDAO.getIp());
        assertTrue(countVisitor.getProductId() == fromDAO.getProductId());
        assertTrue(countVisitor.getCounter() == fromDAO.getCounter());

        CountVisitor other = createCountVisitorWithIp(3421);
        long otherId = other.getId();
        CountVisitor otherFromDAO = countVisitorsORMDAO.getById(otherId);
        assertTrue(fromDAO.getId() != otherFromDAO.getId());
    }

    @Test
    public void getCountByProductIdTest() {
        List list = createCountVisitorListWith15Records();
        CountVisitor countVisitor1 = (CountVisitor) list.get(random.nextInt(15));
        CountVisitor countVisitor2 = (CountVisitor) list.get(random.nextInt(15));
        CountVisitor countVisitor3 = (CountVisitor) list.get(random.nextInt(15));

        long productId = countVisitor1.getProductId();
        countVisitor2.setProductId(productId);
        countVisitorsORMDAO.update(countVisitor2);
        countVisitor3.setProductId(productId);
        countVisitorsORMDAO.update(countVisitor3);
        long count1 = countVisitor1.getCounter();
        long count2 = countVisitor2.getCounter();
        long count3 = countVisitor3.getCounter();

        long total = count1 + count2 + count3;
        assertTrue(total == countVisitorsORMDAO.getCountByProductId(productId));
    }

    @Test
    public void getCountByIp() {
        List list = createCountVisitorListWith15Records();
        CountVisitor countVisitor1 = (CountVisitor) list.get(random.nextInt(15));
        CountVisitor countVisitor2 = (CountVisitor) list.get(random.nextInt(15));
        CountVisitor countVisitor3 = (CountVisitor) list.get(random.nextInt(15));

        String ip = countVisitor1.getIp();
        countVisitor2.setIp(ip);
        countVisitorsORMDAO.update(countVisitor2);
        countVisitor3.setIp(ip);
        countVisitorsORMDAO.update(countVisitor3);
        long count1 = countVisitor1.getCounter();
        long count2 = countVisitor2.getCounter();
        long count3 = countVisitor3.getCounter();

        long total = count1 + count2 + count3;
        assertEquals(total, countVisitorsORMDAO.getCountByIp(ip));
    }

    @Test
    public void getSumCountFromAllTableTest() {
        List<CountVisitor> list = createCountVisitorListWith15Records();
        int total = 0;
        for (CountVisitor countVisitor : list) {
            total += countVisitor.getCounter();
        }
        assertTrue(total == countVisitorsORMDAO.getSumCountFromAllTable());
    }

    @Test
    public void getAllCountTest() {
        List list = createCountVisitorListWith15Records();
        List fromDAO = countVisitorsORMDAO.getAllCount();
        assertTrue(list.size() == fromDAO.size());
    }

    @Test
    public void getCountUserByUserIpProductIdTest(){
        CountVisitor countVisitor = createCountVisitorWithIp(234);
        String ip = countVisitor.getIp();
        long productId = countVisitor.getProductId();

        CountVisitor fromDAO = countVisitorsORMDAO.getCountUserByUserIpProductId(ip, productId);
        assertNotNull(fromDAO);
        assertEquals(countVisitor, fromDAO);
    }

    public CountVisitor createCountVisitorWithIp(int idAdress) {
        long categoryId = objectCreator.createCategory();
        long productId = objectCreator.createProduct(categoryId);
        String ip = String.valueOf(idAdress);
        CountVisitor countVisitor = new CountVisitor();
        countVisitor.setIp(ip);
        countVisitor.setProductId(productId);
        countVisitor.setCounter(random.nextInt(1000));
        long countCustomerId = countVisitorsORMDAO.create(countVisitor);
        return countVisitorsORMDAO.getById(countCustomerId);
    }

    public List createCountVisitorListWith15Records() {
        List<CountVisitor> list = new LinkedList<>();
        for (int i = 0; i < 15; i++) {
            CountVisitor countVisitor = createCountVisitorWithIp(i);
            list.add(countVisitor);
        }
        return list;
    }
}
