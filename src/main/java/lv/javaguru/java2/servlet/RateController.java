package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.product.RateService;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.businesslogic.validators.RateValidationService;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class RateController extends MVCController{

    @Autowired
    private RateValidationService rateValidationService;
    @Autowired
    private RateService rateService;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private Notification notification;

    @Override
    protected MVCModel executePost(HttpServletRequest request) {
        long productId = 0;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            User user = userProvider.getUser();
            productId = idFrom(request.getParameter("productId"));
            int rate = Integer.valueOf(request.getParameter("rate"));
            rateService.rate(productId, user, rate);
            return redirectTo(Product.class, productId);
        } catch (NullPointerException e) {
            notification.setError(e);
            return redirectTo(FrontPageController.class);
        } catch (Exception e) {
            notification.setError(e);
            return redirectTo(Product.class, productId);
        }
    }

}
