package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.profilepages.ProfileUpdateService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.UnauthorizedAccessException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.dto.UserProfile;
import lv.javaguru.java2.dto.builders.UserProfileUtil;
import lv.javaguru.java2.servlet.LoginController;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.SpringPathResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping(value = "/profileUpdate")
@Controller
public class ProfileUpdateController {

    @Autowired
    UserProfileUtil userProfileUtil;

    @Autowired
    ProfileUpdateService profileUpdateService;

    @Autowired
    Notification notification;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView executeGet(HttpServletRequest request) {
        try {
            ModelAndView model = new ModelAndView("/profile_update");
            model.addAllObjects(profileUpdateService.model());
            return model;
        } catch (UnauthorizedAccessException e) {
            return SpringPathResolver.redirectTo(LoginController.class);
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
            return SpringPathResolver.redirectTo(FrontPageController.class);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView updateProfile(@RequestParam Map<String, String> param) {
        try {
            UserProfile userProfile = userProfileUtil
                    .build(param.get("fullName"),
                            param.get("email"),
                            param.get("password"),
                            param.get("repeatPassword"));
            profileUpdateService.update(userProfile);
        } catch (NullPointerException e) {
            return new ModelAndView("redirect:error");
        } catch (DBException e) {
            return new ModelAndView("redirect:error");
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
        }
        return SpringPathResolver.redirectTo(ProfileUpdateController.class);
    }
}