package lv.javaguru.java2.servlet;

import javax.servlet.http.HttpServletRequest;

public class LogoutController extends MVCController {

    @Override
    public MVCModel doGet(HttpServletRequest request) {

        request.getSession().invalidate();

        return new MVCModel("/index");
    }
}
