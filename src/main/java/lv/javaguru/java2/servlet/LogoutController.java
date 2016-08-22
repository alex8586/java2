package lv.javaguru.java2.servlet;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogoutController extends MVCController {

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        request.getSession().invalidate();
        return new MVCModel("/index");
    }
}
