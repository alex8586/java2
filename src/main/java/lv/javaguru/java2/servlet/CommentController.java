package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.product.ReviewService;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.businesslogic.validators.ReviewValidationService;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.SpringPathResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
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
    @RequestMapping("/product/{productId}/comment")
    protected ModelAndView comment(
            @RequestParam("productId") long productId,
            @RequestParam("comment") String comment) {
        if (comment == null)
            throw new NullPointerException();

        if (comment.isEmpty()) {
            notification.setError(EMPTY_COMMENT);
            return SpringPathResolver.redirectTo(Product.class, productId);
        } else if (!userProvider.authorized()) {
            notification.setError(NO_PERMISSION_TO_COMMENT);
            return SpringPathResolver.redirectTo(Product.class, productId);
        }


        User user = userProvider.getUser();
        if (!reviewValidationService.canComment(user, productId)) {
            notification.setError(CAN_NOT_COMMENT_TODAY);
            return SpringPathResolver.redirectTo(Product.class, productId);
        }
        reviewService.addComment(productId, user, comment);
        return SpringPathResolver.redirectTo(Product.class, productId);
    }
}
