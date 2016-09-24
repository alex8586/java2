package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.profilepages.ProfileService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.UnauthorizedAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(name = "ProfileController")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    Notification notification;

    @RequestMapping(value = "/profile", method = RequestMethod.GET, name = "model")
    public ModelAndView model() {
        try {
            ModelAndView model = new ModelAndView();
            model.addAllObjects(profileService.model());
            return model;
        } catch (UnauthorizedAccessException e) {
            return new ModelAndView("redirect:/login");
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
            return new ModelAndView("/profile");
        }
    }
}
