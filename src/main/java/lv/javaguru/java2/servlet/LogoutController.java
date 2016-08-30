package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.UserLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogoutController extends MVCController {

    @Autowired
    UserLogoutService userLogoutService;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        userLogoutService.logout();
        request.getSession().invalidate();
        return new MVCModel("/index");
    }
}
