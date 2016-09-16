package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserRegistrationService;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.dto.UserProfile;
import lv.javaguru.java2.dto.builders.UserProfileUtil;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping(value = "/registration")
@Controller
public class RegistrationController {

    private static final String SUCCESS_MESSAGE = "Congrats. Registration was successfull. Now you can login";
    @Autowired
    UserProfileUtil userProfileUtil;
    @Autowired
    UserRegistrationService userRegistrationService;
    @Autowired
    Notification notification;

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView executeGet(HttpServletRequest request) {
        try {
            ModelAndView model = new ModelAndView("/registration");
            model.addAllObjects(userRegistrationService.model());
            return model;
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
            return redirectTo(FrontPageController.class);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String executePost(@RequestParam Map<String, String> param) {
        try {
            UserProfile userProfile = userProfileUtil
                    .build(param.get("fullName"),
                            param.get("email"),
                            param.get("password"),
                            param.get("repeatPassword"));
            userRegistrationService.register(userProfile);
            notification.setMessage(SUCCESS_MESSAGE);

        } catch (NullPointerException e) {
            return "redirect:error";
        } catch (DBException e) {
            return "redirect:error";
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
            return "redirect:registration";
        }
        return redirectTo(LoginController.class);
    }


}
