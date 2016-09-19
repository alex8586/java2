package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserRegistrationService;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.dto.UserProfile;
import lv.javaguru.java2.dto.builders.UserProfileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    UserProfileUtil userProfileUtil;
    @Autowired
    UserRegistrationService userRegistrationService;
    @Autowired
    Error error;

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    protected ModelAndView executeGet(HttpServletRequest request) {
        try {
            ModelAndView model = new ModelAndView("/registration");
            Map<String, Object> map = userRegistrationService.model();
            for(Map.Entry<String, Object> entry : map.entrySet()){
                model.addObject(entry.getKey(), entry.getValue());
            }
            return model;
        } catch (ServiceException e) {
            error.setError(e.getMessage());
            return new ModelAndView("/index");
        }
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    protected ModelAndView executePost(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/login");
        try {
            UserProfile userProfile = userProfileUtil
                    .build(request.getParameter("fullName"),
                            request.getParameter("email"),
                            request.getParameter("password"),
                            request.getParameter("repeatPassword"));
            userRegistrationService.register(userProfile);
        } catch (NullPointerException e) {
            return new ModelAndView("/error");
        } catch (DBException e) {
            return new ModelAndView("/error");
        } catch (ServiceException e) {
            error.setError(e.getMessage());
            return new ModelAndView("registration");
        }
        return model;
    }
}
