package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.RecordIsNotAvailable;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.database.ReviewDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Review;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class ReviewValidationServiceImplTest {

    @Mock
    Product product;
    @Mock
    Review todayReview;
    @Mock
    Review yesterdaysReview;
    @Mock
    private ReviewDAO reviewDAO;
    @Mock
    private ProductDAO productDAO;
    @InjectMocks
    private ReviewValidationServiceImpl reviewValidationService;
    private Date today = new Date();
    private Date yesterday = new Date(today.getTime() - (1000 * 60 * 60 * 24));

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(today).when(todayReview).getDate();
        Mockito.doReturn(yesterday).when(yesterdaysReview).getDate();
    }


    @Test(expected = RecordIsNotAvailable.class)
    public void failsWhenCommentOnProductNotExists() throws Exception {
        User user = new User();
        Mockito.doReturn(null).when(productDAO).getById(123L);
        reviewValidationService.canComment(user, 123L);
    }

    @Test
    public void adminCanCommentAnytimeOnExistingProduct() throws ServiceException {
        User user = new User();
        user.setAdmin(true);
        Mockito.doReturn(Mockito.mock(Product.class)).when(productDAO).getById(123L);
        assertTrue(reviewValidationService.canComment(user, 123L));
    }

    @Test
    public void canCommentIfNeverDidBefore() throws ServiceException {
        User user = new User();
        Mockito.doReturn(product).when(productDAO).getById(123L);
        Mockito.doReturn(new ArrayList<Review>()).when(reviewDAO).getByUserAndProduct(product, user);
        reviewValidationService.canComment(user, 123L);
        assertTrue(reviewValidationService.canComment(user, 123L));
    }

    @Test
    public void canCommentIfNoCommentToday() throws ServiceException {
        User user = new User();
        Mockito.doReturn(product).when(productDAO).getById(123L);
        Mockito.doReturn(new ArrayList<Review>()).when(reviewDAO).getByUserAndProduct(product, user);

        List<Review> reviewList = new ArrayList<>();
        reviewList.add(yesterdaysReview);
        reviewList.add(yesterdaysReview);
        Mockito.doReturn(reviewList).when(reviewDAO).getByUserAndProduct(product, user);
        reviewValidationService.canComment(user, 123L);
        assertTrue(reviewValidationService.canComment(user, 123L));
    }

    @Test
    public void cantCommentIfAlreadyCommenedToday() throws ServiceException {
        User user = new User();
        Mockito.doReturn(product).when(productDAO).getById(123L);
        Mockito.doReturn(new ArrayList<Review>()).when(reviewDAO).getByUserAndProduct(product, user);

        List<Review> reviewList = new ArrayList<>();
        reviewList.add(yesterdaysReview);
        reviewList.add(todayReview);
        reviewList.add(yesterdaysReview);
        Mockito.doReturn(reviewList).when(reviewDAO).getByUserAndProduct(product, user);
        reviewValidationService.canComment(user, 123L);
        assertFalse(reviewValidationService.canComment(user, 123L));
    }

}