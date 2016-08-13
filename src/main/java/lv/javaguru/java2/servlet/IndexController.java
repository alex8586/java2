package lv.javaguru.java2.servlet;

import javax.servlet.http.HttpServletRequest;

public class IndexController implements MVCController {

    @Override
    public MVCModel execute(HttpServletRequest request) {
        System.out.println("in indexController");
        return new MVCModel("","/index.jsp");
    }
}

