package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserLoginService;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
@RequestMapping(value = "/login")
@Controller
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private Error error;

    @RequestMapping( method = RequestMethod.GET)
    public ModelAndView executeGet(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/login");
        try {
            return model.addAllObjects(userLoginService.model());
        } catch (Exception e) {
            error.setError(e.getMessage());
            return new ModelAndView("/index");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public String executePost(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            User user = userLoginService.authenticate(email, password);
            userLoginService.login(user);
            request.getSession().setAttribute("user", user);
            return "redirect:profile";
        } catch (ServiceException e) {
            error.setError(e.getMessage());
            return "redirect:login";
        } catch (DBException e) {
            return "redirect:error";
        }
    }
}
