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
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private Error error;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView executeGet(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/login");
        try {
            Map<String, Object> map = userLoginService.model();
            for(Map.Entry<String, Object> entry : map.entrySet()){
                model.addObject(entry.getKey(), entry.getValue());
            }
            return model;
        } catch (Exception e) {
            error.setError(e.getMessage());
            return new ModelAndView("/index");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView executePost(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            User user = userLoginService.authenticate(email, password);
            userLoginService.login(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("/profile");
        } catch (ServiceException e) {
            error.setError(e.getMessage());
            return new ModelAndView("/login");
        } catch (DBException e) {
            return new ModelAndView("/error");
        }
    }
}
