package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.profilepages.ProfileOrderService;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.businesslogic.validators.OrderAccessValidationService;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileOrderController {

    @Autowired
    private ProfileOrderService profileOrderService;
    @Autowired
    private OrderAccessValidationService orderAccessValidationService;
    @Autowired
    private UserProvider userProvider;

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public ModelAndView model(@PathVariable("orderId") long orderId) {
        ModelAndView model = new ModelAndView("/profile_order");
        if (!userProvider.authorized())
            return new ModelAndView("redirect:/index");
        User user = userProvider.getUser();
        if (!orderAccessValidationService.isValid(orderId, user.getId())) {
            return new ModelAndView("redirect:/index");
        }
        model.addAllObjects(profileOrderService.getById(orderId));
        return model;
    }

    @RequestMapping(value = "/order/{orderId}/key/{key}", method = RequestMethod.GET)
    public ModelAndView model(@PathVariable("orderId") long orderId,
                              @PathVariable("key") String key) {
        if (userProvider.authorized())
            return new ModelAndView("redirect:/index");
        if (!orderAccessValidationService.isValid(orderId, key)) {
            return new ModelAndView("redirect:/index");
        }
        ModelAndView model = new ModelAndView("/visitor_order");
        model.addAllObjects(profileOrderService.getById(orderId));
        return model;
    }
}
