package lv.javaguru.java2.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by alex.76 on 13.08.2016.
 */
public class RegistrationPageController implements MVCController {
    @Override
    public MVCModel execute(HttpServletRequest request) {

        return new MVCModel("","/registration.jsp");
    }
}
