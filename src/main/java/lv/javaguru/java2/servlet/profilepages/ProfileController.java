package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.profilepages.ProfileService;
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

@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    Notification notification;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView executeGet(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        try {
            model.addAllObjects(profileService.model());
            return model;
        } catch (UnauthorizedAccessException e) {
            //return redirectTo(LoginController.class);
            return new ModelAndView("/login");
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
            //return redirectTo(ProfileService.class);
            return new ModelAndView("/profile");
        }
    }
}
