package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.checkout.CartProvider;
import lv.javaguru.java2.businesslogic.checkout.CheckoutService;
import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.dto.ShippingDetails;
import lv.javaguru.java2.dto.builders.ShippingDetailsUtil;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class CheckoutController extends MVCController {


    @Autowired
    CheckoutService checkoutService;
    @Autowired
    UserProvider userProvider;
    @Autowired
    private ShippingDetailsUtil shippingDetailsUtil;
    @Autowired
    private CartProvider cartProvider;
    @Autowired
    private Notification notification;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        try {
            Map<String, Object> data = checkoutService.model();
            return new MVCModel(data, "/checkout.jsp");
        } catch (ServiceException e) {
            return redirectTo(FrontPageController.class);
        }
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        try {
            ShippingDetails shippingDetails =
                    shippingDetailsUtil.build(
                            request.getParameter("profileId"),
                            request.getParameter("person"),
                            request.getParameter("address"),
                            request.getParameter("phone"),
                            request.getParameter("document"));
            String hashcode = request.getParameter("hashcode");

            Order order = checkoutService.checkout(hashcode, userProvider.getUser(),
                    cartProvider.getCart(), shippingDetails);
            return redirectTo(order);

        } catch (NullPointerException e) {
            return new MVCModel("/notification");
        } catch (DBException e) {
            return new MVCModel("/notification");
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
            return redirectTo(CheckoutController.class);
        }
    }
}
