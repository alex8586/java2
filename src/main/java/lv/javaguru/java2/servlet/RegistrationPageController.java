package lv.javaguru.java2.servlet;

import javax.servlet.http.HttpServletRequest;

public class RegistrationPageController implements MVCController {

    @Override
    public MVCModel execute(HttpServletRequest request) {
        System.out.println("in RegistrationPageController ");
        return new MVCModel("","/registration.jsp");
    }
}
