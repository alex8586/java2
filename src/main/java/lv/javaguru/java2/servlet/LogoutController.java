package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.user.UserLogoutService;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.SpringPathResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

    @Autowired
    UserLogoutService userLogoutService;

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        userLogoutService.logout();
        return SpringPathResolver.redirectTo(FrontPageController.class);
    }
}
