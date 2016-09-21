package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserLoginService;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.SpringPathResolver;
import lv.javaguru.java2.servlet.profilepages.ProfileController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/login")
@Controller
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private Notification notification;

    @RequestMapping( method = RequestMethod.GET)
    public ModelAndView model() {
        ModelAndView model = new ModelAndView("/login");
        try {
            return model.addAllObjects(userLoginService.model());
        } catch (Exception e) {
            notification.setError(e.getMessage());
            return SpringPathResolver.redirectTo(FrontPageController.class);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView login(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        try {
            User user = userLoginService.authenticate(email, password);
            userLoginService.login(user);
            return SpringPathResolver.redirectTo(ProfileController.class);
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
            return SpringPathResolver.redirectTo(LoginController.class);
        } catch (DBException e) {
            return new ModelAndView("redirect:error");
        }
    }
}
