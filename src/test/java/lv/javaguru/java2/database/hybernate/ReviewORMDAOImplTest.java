package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.database.ReviewDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Review;
import lv.javaguru.java2.domain.User;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class ReviewORMDAOImplTest extends CrudHybernateDAOTest<Review, ReviewDAO> {

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
    private ReviewDAO reviewDAO;

    private User user;
    private Product product;
    private User anotherUser;
    private Product anotherProduct;
    private Date date = new Date();

    @Before
    public void before() {
        databaseCleaner.cleanDatabase();
        this.user = userDAO.getById(objectCreator.createUser());
        this.anotherUser = userDAO.getById(objectCreator.createUser());

        long productId = objectCreator.createProduct(objectCreator.createCategory());
        this.product = productDAO.getById(productId);

        long anotherProductId = objectCreator.createProduct(objectCreator.createCategory());
        this.anotherProduct = productDAO.getById(anotherProductId);
        super.before();
    }

    @Override
    protected void fillRecordWithData(Review review) {
        review.setUserId(user.getId());
        review.setProductId(product.getId());
        review.setUserName(user.getFullName());
        review.setComment("comment");
        review.setDate(new Date());
    }

    @Override
    protected void makeChangesInRecord(Review review) {
        review.setUserId(anotherUser.getId());
        review.setProductId(anotherProduct.getId());
        review.setUserName(anotherUser.getFullName());
        review.setComment("new comment");
        review.setDate(new Date(date.getTime() + (60 * 60 * 24)));
    }

    @Override
    protected void compareRecords(Review review1, Review review2) {
        assertEquals(review1.getId(), review2.getId());
        assertEquals(review1.getUserId(), review2.getUserId());
        assertEquals(review1.getUserName(), review2.getUserName());
        assertEquals(review1.getComment(), review2.getComment());
        assertEquals(DateUtils.truncate(review1.getDate(), Calendar.DATE),
                DateUtils.truncate(review2.getDate(), Calendar.DATE));

    }

    @Test
    public void getByProductTest() {
        Review review = reviewDAO.getById(objectCreator.createReview());
        long productId = review.getProductId();

        Review second = reviewDAO.getById(objectCreator.createReview());
        second.setProductId(productId);
        reviewDAO.update(second);

        List<Review> list = reviewDAO.getByProduct(productDAO.getById(productId));
        assertTrue(list.size() == 2);
    }

    @Test
    public void getByUserTest() {
        long reviewId = objectCreator.createReview();
        Review review = reviewDAO.getById(reviewId);
        long userId = review.getUserId();

        Review second = reviewDAO.getById(objectCreator.createReview());
        second.setUserId(userId);
        reviewDAO.update(second);

        Review third = reviewDAO.getById(objectCreator.createReview());

        List<Review> list = reviewDAO.getByUser(userDAO.getById(userId));
        assertTrue(list.size() == 2);
    }

    @Test
    public void getByUserAndProductTest() {
        Review review = reviewDAO.getById(objectCreator.createReview());
        long userId = review.getUserId();
        long productId = review.getProductId();

        Review other = reviewDAO.getById(objectCreator.createReview());
        List<Review> list = reviewDAO.getByUserAndProduct(productDAO.getById(productId), userDAO.getById(userId));

        assertTrue(list.size() == 1);
        assertEquals(review, list.get(0));
    }
}
