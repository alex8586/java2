package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.product.ReviewService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.mvc.MVCController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController extends MVCController {

    private static final String NO_PERMISSION_TO_COMMENT = "You have no permission to review";
    private static final String EMPTY_COMMENT = "Comment can't be empty";

    @Autowired
    Notification notification;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/product/comment", method = RequestMethod.POST)
    protected String review(
            @RequestParam("productId") long productId,
            @RequestParam("commentText") String comment) {
        if (comment == null)
            throw new NullPointerException();

        if (comment.isEmpty()) {
            notification.setError(EMPTY_COMMENT);
            return "redirect:/product/" + productId;
        } else if (!userProvider.authorized()) {
            notification.setError(NO_PERMISSION_TO_COMMENT);
            return "redirect:/product/" + productId;
        }

        User user = userProvider.getUser();
        try {
            reviewService.addComment(productId, user, comment);
            return "redirect:/product/" + productId;
        } catch (ServiceException e) {
            notification.setError(e);
        }
        return "redirect:/product/" + productId;
    }
}
