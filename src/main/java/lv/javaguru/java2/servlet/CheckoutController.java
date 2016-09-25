package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.checkout.CartProvider;
import lv.javaguru.java2.businesslogic.checkout.CheckoutService;
import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.dto.ShippingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@Controller
@RequestMapping(value = "/checkout", name = "CheckoutController")
public class CheckoutController {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", Locale.ENGLISH);
    @Autowired
    CheckoutService checkoutService;
    @Autowired
    UserProvider userProvider;
    @Autowired
    private CartProvider cartProvider;
    @Autowired
    private Notification notification;

    @RequestMapping(method = RequestMethod.GET, name = "checkout")
    public ModelAndView checkout() {
        ModelAndView model = new ModelAndView("/checkout");
        try {
            model.addAllObjects(checkoutService.model());
            return model;
        } catch (ServiceException e) {
            return new ModelAndView("redirect:/index");
        }
    }

    @RequestMapping(method = RequestMethod.POST, name = "buy")
    public String buy(@ModelAttribute ShippingDetails shippingDetails,
                      @RequestParam("hashcode") String hashcode,
                      @RequestParam("deliveryDate") String deliveryDateString) {
        try {
            LocalDate deliveryDate = LocalDate.parse(deliveryDateString, formatter);
            Order order = checkoutService.checkout(hashcode,
                    userProvider.getUser(), cartProvider.getCart(),
                    shippingDetails, deliveryDate);
            if (userProvider.authorized())
                return "redirect:/order/" + order.getId();
            else
                return "redirect:/index";
        } catch (NullPointerException e) {
            return "redirect:error";
        } catch (DBException e) {
            return "redirect:error";
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
            return "redirect:/checkout";
        }
    }
}
