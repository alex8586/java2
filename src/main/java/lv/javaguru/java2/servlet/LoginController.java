package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserLoginService;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import lv.javaguru.java2.servlet.profilepages.ProfileController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping(value = "/login")
@Controller
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private Notification notification;

    @RequestMapping( method = RequestMethod.GET)
    public ModelAndView executeGet(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/login");
        try {
            return model.addAllObjects(userLoginService.model());
        } catch (Exception e) {
            notification.setError(e.getMessage());
            //return redirectTo(FrontPageController.class);
            return new ModelAndView("/index");//redir
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public String executePost(@RequestParam Map<String, String> param, HttpServletRequest request) {
        String email = param.get("email");
        String password = param.get("password");
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
