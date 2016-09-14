package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.user.UserLoginService;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import lv.javaguru.java2.servlet.profilepages.ProfileController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class LoginController extends MVCController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private Error error;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        try {
            Map<String, Object> map = userLoginService.model();
            return new MVCModel(map, "/login.jsp");
        } catch (Exception e) {
            error.setError(e.getMessage());
            return redirectTo(FrontPageController.class);
        }
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            User user = userLoginService.authenticate(email, password);
            userLoginService.login(user);
            request.getSession().setAttribute("user", user); //old style
            return redirectTo(ProfileController.class);
        } catch (ServiceException e) {
            error.setError(e.getMessage());
            return redirectTo(LoginController.class);
        } catch (DBException e) {
            return new MVCModel("/error");
        }
    }
}
