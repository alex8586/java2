package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.product.ProductProvider;
import lv.javaguru.java2.businesslogic.product.ReviewService;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.businesslogic.validators.ReviewValidationService;
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
    private UserProvider userProvider;
    @Autowired
    private ProductProvider productProvider;
    @Autowired
    private ReviewValidationService reviewValidationService;
    @Autowired
    private ReviewService reviewService;

    @Override
    protected MVCModel executePost(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userProvider.getUser();
        if (request.getParameter("comment") != null) {
            if(user == null){
                map.put("error", NO_PERMISSION_TO_COMMENT);
                return new MVCModel(map, "/product.jsp");
            }

            String comment = request.getParameter("comment");
            if(comment == null || comment.isEmpty()){
                map.put("error", EMPTY_COMMENT);
                return new MVCModel(map, "/product.jsp");
            }
            long productId = Long.parseLong(request.getParameter("productId"));
            productProvider.setProductId(productId);
            request.getSession().setAttribute("productId", productId);
            if(!reviewValidationService.canComment(user, productId)){
                map.put("error", CAN_NOT_COMMENT_TODAY);
                return new MVCModel(map, "/product.jsp");
            }
            reviewService.addComment(comment);
        }
        return redirectTo(ProductController.class);
    }
}
