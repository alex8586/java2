package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.product.RateService;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RateController extends MVCController{

    @Autowired
    private RateService rateService;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private Notification notification;

    @RequestMapping("/product/{productId}/rate/{rate}")
    protected MVCModel rate(
            @RequestParam("productId") long productId,
            @RequestParam("rate") int rate) {
        try {
            User user = userProvider.getUser();
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
