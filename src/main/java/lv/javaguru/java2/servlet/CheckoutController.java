package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.CartProvider;
import lv.javaguru.java2.businesslogic.CheckoutService;
import lv.javaguru.java2.businesslogic.ShippingProfileService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.validators.ShippingDetailFormatValidationService;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class CheckoutController extends MVCController {
    private final String EMPTY_FIELDS = "All fields must be filled";
    @Autowired
    ShippingProfileService shippingProfileService;
    @Autowired
    private CheckoutService checkoutService;
    @Autowired
    private CartProvider cartProvider;
    @Autowired
    private ShippingDetailFormatValidationService shippingDetailFormatValidationService;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> data = checkoutService.serve();
        return new MVCModel(data, "/checkout.jsp");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {

        if (cartProvider.getCart().hashCode() != Long.valueOf(request.getParameter("hashcode")))
            return redirectTo(CheckoutController.class);

        ShippingProfile shippingProfile = new ShippingProfile();
        shippingProfile.setAddress(request.getParameter("address"));
        shippingProfile.setPerson(request.getParameter("person"));
        shippingProfile.setDocument(request.getParameter("document"));
        shippingProfile.setPhone(request.getParameter("phone"));
        if (request.getParameter("profileId") != null) {
            try {
                long profileId = Long.valueOf(request.getParameter("profileId"));
                if (profileId > 0)
                    shippingProfile.setId(profileId);
            } catch (NumberFormatException e) {

            }
        }

        try {
            shippingDetailFormatValidationService.validate(shippingProfile);
        } catch (Exception e) {
            request.getSession().setAttribute("profileError", EMPTY_FIELDS);
            return redirectTo(CheckoutController.class);
        }

        Order order = checkoutService.createOrder(cartProvider.getCart(), shippingProfile);
        cartProvider.empty();
        request.getSession().removeAttribute("cart");
        request.getSession().removeAttribute("cartPrice");

        try {
            if (shippingProfile.getId() == 0)
                shippingProfileService.save(shippingProfile);
        } catch (ServiceException e) {

        }
        return redirectTo(ProfileHistoryController.class);
    }
}
