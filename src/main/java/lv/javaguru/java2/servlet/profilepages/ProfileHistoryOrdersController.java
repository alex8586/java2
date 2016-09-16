package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.profilepages.ProfileOrderService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.UnauthorizedAccessException;
import lv.javaguru.java2.servlet.LoginController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ProfileHistoryOrdersController {

    @Autowired
    private ProfileOrderService profileOrderService;

    @Autowired
    private Notification notification;

    @RequestMapping(value = "/profileHistoryOrders", method = RequestMethod.GET)
    public MVCModel executeGet(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/profile_history");
        try {
            model.addAllObjects(profileOrderService.model());
            return model;
        } catch (UnauthorizedAccessException e) {
            return new ModelAndView("/login");//redir
        } catch (ServiceException e) {
            notification.getError();
            return new ModelAndView("/profile_history");//should redirect
        }
    }
}
