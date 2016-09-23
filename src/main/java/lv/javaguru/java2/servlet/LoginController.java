package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserLoginService;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.SpringPathResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/login")
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
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpServletRequest request){
        try {
            User user = userLoginService.authenticate(email, password);
            userLoginService.login(user);
            request.getSession().setAttribute("user", user);
            return "redirect:profile";
        } catch (ServiceException e) {
            notification.setError(e.getMessage());
            return "redirect:login";
        } catch (DBException e) {
            return "redirect:error";
        }
    }
}
