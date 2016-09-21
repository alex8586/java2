package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.profilepages.ProfileOrderService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.UnauthorizedAccessException;
import lv.javaguru.java2.servlet.LoginController;
import lv.javaguru.java2.servlet.mvc.SpringPathResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileHistoryOrdersController {

    @Autowired
    private ProfileOrderService profileOrderService;

    @RequestMapping(value = "/profileHistoryOrders", method = RequestMethod.GET)
    public ModelAndView model() {
        ModelAndView model = new ModelAndView("/profile_history");
        try {
            model.addAllObjects(profileOrderService.model());
            return model;
        } catch (UnauthorizedAccessException e) {
            return SpringPathResolver.redirectTo(LoginController.class);
        } catch (ServiceException e) {
            return new ModelAndView("redirect:error");
        }
    }
}
