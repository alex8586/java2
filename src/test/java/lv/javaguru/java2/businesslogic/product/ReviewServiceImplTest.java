package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.businesslogic.validators.ReviewValidationService;
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

import static org.mockito.Matchers.any;

public class ReviewServiceImplTest {

    @Mock
    private ReviewDAO reviewDAO;

    @Mock
    private ReviewValidationService reviewValidationService;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Mock
    private User user;

    @Mock
    private Review review;

    @Mock
    private Product product;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = WrongFieldFormatException.class)
    public void testEmptyText() throws ServiceException {
        reviewService.addReview(123l, user, "");
    }

    @Test(expected = ServiceException.class)
    public void cantCommentAnotherTimeOerDay() throws ServiceException {
        Mockito.doReturn(false).when(reviewValidationService).canComment(user, 123);
        reviewService.addReview(123l, user, "text");
    }

    @Test
    public void successWhenEverythingIsFine() throws ServiceException {
        Mockito.doReturn(true).when(reviewValidationService).canComment(user, 123);
        reviewService.addReview(123l, user, "text");
        Mockito.verify(reviewDAO, Mockito.times(1)).create(any());
    }

}