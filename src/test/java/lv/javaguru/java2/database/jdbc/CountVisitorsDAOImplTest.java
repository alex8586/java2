package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.CountVisitorsDAO;
import lv.javaguru.java2.database.hybernate.CountClassHelper;
import lv.javaguru.java2.domain.CountVisitor;
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
public class CountVisitorsDAOImplTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private CountClassHelper countClassHelper;
    @Qualifier("JDBC_CountVisitorsDAO")
    @Autowired
    private CountVisitorsDAO countVisitorsDAO;

    private Random random = new Random();

    @Before
    public void before() {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void createCountUserTest() {
        long categoryId = countClassHelper.createCategory();
        long productId = countClassHelper.createProduct(categoryId);

        CountVisitor countVisitor = new CountVisitor();
        countVisitor.setIp("111.222.333.444");
        countVisitor.setProductId(productId);
        countVisitor.setCounter(0);
        long countVisitorId = countVisitorsDAO.create(countVisitor);

        assertTrue(countVisitorId > 0);
        CountVisitor countVisitorFromDAO = countVisitorsDAO.getById(countVisitorId);
        assertEquals(countVisitor.getIp(), countVisitorFromDAO.getIp());
        assertEquals(countVisitor.getCounter(), countVisitorFromDAO.getCounter());
        assertEquals(countVisitor.getProductId(), countVisitorFromDAO.getProductId());
    }

    @Test
    public void updateCountUserTest() {
        CountVisitor countVisitor1 = createCountVisitor();
        CountVisitor countVisitor2 = createCountVisitor();
        long id = countVisitor1.getId();
        String ip = countVisitor1.getIp();
        long productId = countVisitor1.getProductId();
        int counter = countVisitor1.getCounter();

        countVisitor1.setIp(countVisitor2.getIp());
        countVisitor1.setProductId(countVisitor2.getProductId());
        countVisitor1.setCounter(countVisitor2.getCounter());

        countVisitorsDAO.update(countVisitor1);
        assertTrue(id == countVisitor1.getId());
        assertNotEquals(ip, countVisitor1.getIp());
        assertTrue(productId != countVisitor1.getProductId());
        assertTrue(counter != countVisitor1.getCounter());
    }

    @Test
    public void deleteCountUserTest() {
        CountVisitor countVisitor = createCountVisitor();
        long id = countVisitor.getId();

        countVisitorsDAO.delete(countVisitor);
        assertNull(countVisitorsDAO.getById(id));
    }

    @Test
    public void getById() {
        CountVisitor countVisitor = createCountVisitor();
        long id = countVisitor.getId();

        CountVisitor fromDAO = countVisitorsDAO.getById(id);
        assertTrue(countVisitor.getId() == fromDAO.getId());
        assertEquals(countVisitor.getIp(),fromDAO.getIp());
        assertTrue(countVisitor.getProductId() == fromDAO.getProductId());
        assertTrue(countVisitor.getCounter() == fromDAO.getCounter());

        CountVisitor other = createCountVisitor();
        long otherId = other.getId();
        CountVisitor otherFromDAO = countVisitorsDAO.getById(otherId);
        assertTrue(fromDAO.getId() != otherFromDAO.getId());
    }

    @Test
    public void getCountByProductIdTest() {
        List list = createCountVisitorListWith15Records();
        CountVisitor countVisitor1= (CountVisitor) list.get(random.nextInt(15));
        CountVisitor countVisitor2 = (CountVisitor) list.get(random.nextInt(15));
        CountVisitor countVisitor3 = (CountVisitor) list.get(random.nextInt(15));

        long productId = countVisitor1.getProductId();
        countVisitor2.setProductId(productId);
        countVisitorsDAO.update(countVisitor2);
        countVisitor3.setProductId(productId);
        countVisitorsDAO.update(countVisitor3);
        int count1 = countVisitor1.getCounter();
        int count2 = countVisitor2.getCounter();
        int count3 = countVisitor3.getCounter();

        int total = count1 + count2 + count3;
        assertTrue(total == countVisitorsDAO.getCountByProductId(productId));
    }

    @Test
    public void getCountByIp() {
        List list = createCountVisitorListWith15Records();
        CountVisitor countVisitor1 = (CountVisitor) list.get(random.nextInt(15));
        CountVisitor countVisitor2= (CountVisitor) list.get(random.nextInt(15));
        CountVisitor countVisitor3 = (CountVisitor) list.get(random.nextInt(15));

        String ip = countVisitor1.getIp();
        countVisitor2.setIp(ip);
        countVisitorsDAO.update(countVisitor2);
        countVisitor3.setIp(ip);
        countVisitorsDAO.update(countVisitor3);
        int count1 = countVisitor1.getCounter();
        int count2 = countVisitor2.getCounter();
        int count3 = countVisitor3.getCounter();

        int total = count1 + count2 + count3;
        assertTrue(total == countVisitorsDAO.getCountByIp(ip));
    }

    @Test
    public void getSumCountFromAllTableTest() {
        List<CountVisitor> list = createCountVisitorListWith15Records();
        int total = 0;
        for(CountVisitor countVisitor : list){
            total += countVisitor.getCounter();
        }
        assertTrue(total == countVisitorsDAO.getSumCountFromAllTable());
    }

    @Test
    public void getAllCountTest() {
        List list = createCountVisitorListWith15Records();
        List fromDAO = countVisitorsDAO.getAllCount();
        assertTrue(list.size() == fromDAO.size());
    }

    public CountVisitor createCountVisitor() {
        long categoryId = countClassHelper.createCategory();
        long productId = countClassHelper.createProduct(categoryId);
        String ip1 = String.valueOf(random.nextInt(1000));
        String ip2 = String.valueOf(random.nextInt(1000));
        String ip3 = String.valueOf(random.nextInt(1000));
        String ip4 = String.valueOf(random.nextInt(1000));
        String ip = ip1 + "." + ip2 + "." + ip3 + "." + ip4;

        CountVisitor countVisitor = new CountVisitor();
        countVisitor.setIp(ip);
        countVisitor.setProductId(productId);
        countVisitor.setCounter(random.nextInt(30000));
        long countCustomerId = countVisitorsDAO.create(countVisitor);
        return countVisitorsDAO.getById(countCustomerId);
    }

    public List createCountVisitorListWith15Records() {
        List<CountVisitor> list = new LinkedList<>();
        for (int i = 0; i < 15; i++) {
            CountVisitor countVisitor = createCountVisitor();
            list.add(countVisitor);
        }
        return list;
    }
}
