package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.user.UserLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {

    @Autowired
    UserLogoutService userLogoutService;

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView executeGet(HttpServletRequest request) {
        userLogoutService.logout();
        request.getSession().invalidate();
        return new ModelAndView("/frontpage");
    }
}
