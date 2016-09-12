package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.user.UserLogoutService;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
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
        return redirectTo(FrontPageController.class);
    }
}
