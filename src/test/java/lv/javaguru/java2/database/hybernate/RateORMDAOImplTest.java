package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.database.RateDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Rate;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class RateORMDAOImplTest extends CrudHybernateDAOTest<Rate, RateDAO> {

    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private ObjectCreator objectCreator;
    @Qualifier("ORM_UserDAO")
    @Autowired
    private UserDAO userDAO;
    @Qualifier("ORM_ProductDAO")
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private RateDAO rateDAO;

    private User user;
    private Product product;
    private User anotherUser;
    private Product anotherProduct;

    @Before
    public void before() {
        databaseCleaner.cleanDatabase();
        long userId = objectCreator.createUser();
        this.user = userDAO.getById(userId);
        long anotherUserId = objectCreator.createUser();
        this.anotherUser = userDAO.getById(anotherUserId);

        long categoryId = objectCreator.createCategory();
        long productId = objectCreator.createProduct(categoryId);
        this.product = productDAO.getById(productId);

        long anotherCategoryId = objectCreator.createCategory();
        long anotherProductId = objectCreator.createProduct(anotherCategoryId);
        this.anotherProduct = productDAO.getById(anotherProductId);

        super.before();
    }

    @Override
    protected void fillRecordWithData(Rate rate) {
        rate.setUserId(user.getId());
        rate.setProductId(product.getId());
        rate.setRate(7);
    }

    @Override
    protected void makeChangesInRecord(Rate rate) {
        rate.setUserId(anotherUser.getId());
        rate.setProductId(anotherProduct.getId());
        rate.setRate(12);
    }

    @Override
    protected void compareRecords(Rate record1, Rate record2) {
        assertEquals(record1.getId(), record2.getId());
        assertEquals(record1.getUserId(), record2.getUserId());
        assertEquals(record1.getProductId(), record2.getProductId());
        assertTrue(record1.getRate() == record2.getRate());
    }

    @Test
    public void getByUserIdAndProductId() {
        Rate rate = new Rate();
        long userId = objectCreator.createUser();
        rate.setUserId(userId);
        long categoryId = objectCreator.createCategory();
        long productId = objectCreator.createProduct(categoryId);
        rate.setProductId(productId);
        rate.setRate(7);

        long id = rateDAO.create(rate);
        Rate created = rateDAO.getById(id);
        Rate fromDAO = rateDAO.getByUserIdAndProductId(userId, productId);

        assertEquals(created, fromDAO);
    }

    @Test(expected = NullPointerException.class)
    public void getAverageRateWithEmptyTable(){
        long categoryId = objectCreator.createCategory();
        long productId = objectCreator.createProduct(categoryId);

        rateDAO.getAverageRate(productId);
    }

    @Test
    public void getAverageRate(){
        long productId = objectCreator.createProduct(objectCreator.createCategory());
        Rate rate = new Rate();
        rate.setProductId(productId);
        rate.setUserId(user.getId());
        rate.setRate(5);
        rateDAO.create(rate);

        Rate otherRate = new Rate();
        otherRate.setProductId(productId);
        otherRate.setUserId(anotherUser.getId());
        otherRate.setRate(2);
        rateDAO.create(otherRate);

        double average = rateDAO.getAverageRate(productId);
        assertEquals(3.5, average, 0);
    }

    @Test
    public void getByProductIdTest(){
        long productId = objectCreator.createProduct(objectCreator.createCategory());
        Rate rate = new Rate();
        rate.setProductId(productId);
        rate.setUserId(user.getId());
        rate.setRate(5);
        rateDAO.create(rate);

        long otherProductId = objectCreator.createProduct(objectCreator.createCategory());
        Rate otherRate = new Rate();
        otherRate.setProductId(otherProductId);
        otherRate.setUserId(anotherUser.getId());
        otherRate.setRate(2);
        rateDAO.create(otherRate);

        List<Rate> result = rateDAO.getByProductId(otherProductId);
        assertTrue(result.size() == 1);
        assertEquals(otherProductId, result.get(0).getProductId());
    }

}
