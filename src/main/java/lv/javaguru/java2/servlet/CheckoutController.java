package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.checkout.CartProvider;
import lv.javaguru.java2.businesslogic.checkout.CheckoutService;
import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.profilepages.ShippingProfileService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.dto.ShippingDetails;
import lv.javaguru.java2.dto.builders.ShippingDetailsUtil;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import lv.javaguru.java2.servlet.profilepages.ProfileHistoryOrdersController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class CheckoutController extends MVCController {

    @Autowired
    ShippingProfileService shippingProfileService;
    @Autowired
    private CheckoutService checkoutService;
    @Autowired
    private ShippingDetailsUtil shippingDetailsUtil;
    @Autowired
    private CartProvider cartProvider;
    @Autowired
    private Error error;
    @Autowired
    private UserProvider userProvider;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        if (cartProvider.getCart().getAll().size() == 0)
            return redirectTo(FrontPageController.class);

        Map<String, Object> data = checkoutService.model();
        return new MVCModel(data, "/checkout.jsp");
    }

    @Override
    @Transactional
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
            Order order = checkoutService.createOrder(cartProvider.getCart(), hashcode, shippingDetails);

            //stockService.substract(cartprovide.getCart());
            cartProvider.empty();
            request.getSession().removeAttribute("cart");
            request.getSession().removeAttribute("cartPrice");
            if (shippingDetails.getId() == 0 && userProvider.authorized()) {
                try {
                    shippingProfileService.save(shippingDetails);
                } catch (Exception e) {

                }
            }
        } catch (NullPointerException e) {
            return new MVCModel("/error");
        } catch (DBException e) {
            return new MVCModel("/error");
        } catch (ServiceException e) {
            error.setError(e.getMessage());
            return redirectTo(CheckoutController.class);
        }
        System.out.println("successfully leaving checkout after making order");
        return redirectTo(ProfileHistoryOrdersController.class);
    }
}
