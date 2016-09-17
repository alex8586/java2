package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.product.ReviewService;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.businesslogic.validators.ReviewValidationService;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommentController extends MVCController {

    private static final String NO_PERMISSION_TO_COMMENT = "You have no permission to comment";
    private static final String EMPTY_COMMENT = "Comment can't be empty";
    private static final String CAN_NOT_COMMENT_TODAY = "Not allowed to comment twice per day";
    @Autowired
    Notification notification;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private ReviewValidationService reviewValidationService;
    @Autowired
    private ReviewService reviewService;

    @Override
    protected MVCModel executePost(HttpServletRequest request) {

        long productId = idFrom(request.getParameter("productId"));
        String comment = request.getParameter("comment");
        if (comment == null)
            throw new NullPointerException();

        Map<String, Object> map = new HashMap<String, Object>();
        if (comment.isEmpty()) {
            notification.setError(EMPTY_COMMENT);
            return redirectTo(Product.class, productId);
        }
        if (!userProvider.authorized()) {
            notification.setError(NO_PERMISSION_TO_COMMENT);
            return redirectTo(Product.class, productId);
        }

        User user = userProvider.getUser();
        if (!reviewValidationService.canComment(user, productId)) {
            notification.setError(CAN_NOT_COMMENT_TODAY);
            return redirectTo(Product.class, productId);
        }
        reviewService.addComment(comment);
        return redirectTo(ProductController.class, productId);
    }
}
