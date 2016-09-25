package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.profilepages.ProfileOrderService;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.businesslogic.validators.ProfileOrderValidationService;
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
    private ProfileOrderValidationService profileOrderValidationService;
    @Autowired
    private UserProvider userProvider;

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public ModelAndView model(@PathVariable("orderId") long orderId) {
        ModelAndView model = new ModelAndView("/profile_order");
        if (!userProvider.authorized())
            return new ModelAndView("redirect:/index");
        User user = userProvider.getUser();
        if(!profileOrderValidationService.isValid(orderId, user.getId())){
            return new ModelAndView("redirect:/index");
        }
        model.addAllObjects(profileOrderService.getById(orderId));
        return model;
    }

    @RequestMapping(value = "/order/{orderId}/securityKey/{securityKey}", method = RequestMethod.GET)
    public ModelAndView model(@PathVariable("orderId") long orderId, @PathVariable("securityKey") String securityKey) {
        if (userProvider.authorized())
            return new ModelAndView("redirect:/index");
        if (!profileOrderValidationService.isValid(orderId, securityKey)) {
            return new ModelAndView("redirect:/index");
        }
        ModelAndView model = new ModelAndView("/profile_order");
        model.addAllObjects(profileOrderService.getById(orderId));
        return model;
    }
}
